package io.crossbar.autobahn.sdjx.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.guoqi.actionsheet.ActionSheet;
import com.squareup.okhttp.Request;

import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.qqtheme.framework.picker.DateTimePicker;
import io.crossbar.autobahn.sdjx.MyApplication;
import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.bean.AskforleaveBean;
import io.crossbar.autobahn.sdjx.bean.InfobytelBean;
import io.crossbar.autobahn.sdjx.bean.InterfacefourBean;
import io.crossbar.autobahn.sdjx.db.Note;
import io.crossbar.autobahn.sdjx.db.Note_;
import io.crossbar.autobahn.sdjx.db.SubmitNote;
import io.crossbar.autobahn.sdjx.db.SubmitNote_;
import io.crossbar.autobahn.sdjx.manager.HttpClientUtil;
import io.crossbar.autobahn.sdjx.manager.HttpManager;
import io.crossbar.autobahn.sdjx.manager.MultiUpload;
import io.crossbar.autobahn.sdjx.utils.DateUtils;
import io.crossbar.autobahn.sdjx.utils.LQRPhotoSelectUtils;
import io.crossbar.autobahn.sdjx.utils.PreferenceUtil;
import io.crossbar.autobahn.sdjx.widget.MyImageDialog;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

import static io.crossbar.autobahn.sdjx.manager.HttpManager.BaseUrl;

/**
 * Created by Administrator on 2018/2/6 0006.
 * 请假单
 */

public class AskForLeaveActivity extends BaseActivity implements ActionSheet.OnActionSheetSelected {
    public int PROCESS_COUNT = 0;//流程路线
    public int itemId = 0;//记录添加图片view的id
    public int countid = 0;//记录审批人需要点击的索引
    private String TAG = "AskForLeaveActivity";
    private String imgpath = "";//图片抵地址
    private TextView content_tv_title;
    private EditText et_askforleave_reason;
    private ImageView imgExit;

    private LinearLayout ll_back;
    //    private LinearLayout ll_askforleave_certainperson;
    private LinearLayout lv_person_contain;//审批人流程容器
    private RelativeLayout rl_start;
    private RelativeLayout rl_end;
    private RelativeLayout rl_askforleave_days;
    private TextView tv_askforleave_starttime;
    private TextView tv_askforleave_endtime;
    private EditText tv_askforleave_totaltime;
    private Spinner spinner;
    public String ASKFORLEAVETYPE = "1";//请假类型
    public String LEAVEDAYS = "";//请假天数
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    HashMap<Integer, RelativeLayout> conditions = new HashMap<Integer, RelativeLayout>();
    HashMap<Integer, HorizontalScrollView> conditionpeople = new HashMap<Integer, HorizontalScrollView>();
    private String arr_pname = "";//回调审核人
    private String arr_strflow_note_id = "";
    private String arr_complayid = "";
    private String arr_departmentid = "";
    private String arr_tdepartmentid = "";
    private String arr_position_id = "";
    private String arr_tel = "";
    //    private List<String> flow_no = new ArrayList<>();
    int selectProgressid;//选中的流程
    //数据库
    private Box<Note> notesBox;
    private Query<Note> notesQuery;
    private Box<SubmitNote> SubmitnotesBox;
    private Query<SubmitNote> SubmitnotesQuery;
    List list = new ArrayList();
    List list1 = new ArrayList();
    List list2 = new ArrayList();
    List list3 = new ArrayList();
    List list4 = new ArrayList();
    List list5 = new ArrayList();
    List list6 = new ArrayList();
    List<String> picturepatch = new ArrayList<>();
    //    List<Uri> Mypicturelist = new ArrayList<>();
    List<File> Mypicturefilelist = new ArrayList<>();
    private boolean ischeck = false;//是否选中流程

    private ImageView iv_askforleace_fujian;
    private LinearLayout ll_askforleave_certainpicture;

    @SuppressLint("WrongViewCast")
    @Override
    public void initView() {

        //获取数据库对象
        BoxStore boxStore = MyApplication.getInstance().getBoxStore();
        notesBox = boxStore.boxFor(Note.class);
        notesQuery = notesBox.query().order(Note_.id).build();
        SubmitnotesBox = boxStore.boxFor(SubmitNote.class);
        SubmitnotesQuery = SubmitnotesBox.query().order(SubmitNote_.id).build();
        notesBox.removeAll();
        SubmitnotesBox.removeAll();

        setContentView(R.layout.activity_askforleave);
        content_tv_title = findViewById(R.id.content_tv_title);
        spinner = findViewById(R.id.spinner);
        rl_askforleave_days = findViewById(R.id.rl_askforleave_days);
        lv_person_contain = findViewById(R.id.lv_person_contain);
        et_askforleave_reason = findViewById(R.id.et_askforleave_reason);
        ll_askforleave_certainpicture = findViewById(R.id.ll_askforleave_certainpicture);
        iv_askforleace_fujian = findViewById(R.id.iv_askforleace_fujian);

        ll_back = findViewById(R.id.ll_back);
        rl_start = findViewById(R.id.rl_askforleave_start);
        rl_end = findViewById(R.id.rl_askforleave_end);
        tv_askforleave_starttime = findViewById(R.id.tv_askforleave_starttime);
        tv_askforleave_endtime = findViewById(R.id.tv_askforleave_endtime);
        tv_askforleave_totaltime = findViewById(R.id.tv_askforleave_totaltime);
        tv_askforleave_starttime.setText(DateUtils.getTodayDateTime().substring(0, 16));
        tv_askforleave_endtime.setText(DateUtils.getTodayDateTime().substring(0, 16));
        content_tv_title.setText("请假单");
        imgExit = findViewById(R.id.imgExit);
        imgExit.setImageResource(R.mipmap.ic_back);
// 1、创建LQRPhotoSelectUtils（一个Activity对应一个LQRPhotoSelectUtils）
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, new LQRPhotoSelectUtils.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                imgpath = outputFile.getAbsolutePath();
                Log.d(TAG, "图片地址" + outputUri);
//                Mypicturelist.add(outputUri);
                picturepatch.add(imgpath);
                Mypicturefilelist.add(outputFile);
                // 4、当拍照或从图库选取图片成功后回调
//                mTvPath.setText(outputFile.getAbsolutePath());
//                mTvUri.setText(outputUri.toString());
//                ImageView imageView = new ImageView(AskForLeaveActivity.this);
//                imageView.setLayoutParams(new LinearLayout.LayoutParams(100,100));  //设置图片宽高
                View myview = View.inflate(AskForLeaveActivity.this, R.layout.item_askforlaeve_picture, null);
                ImageView imageView = myview.findViewById(R.id.ivpicture);
                ImageView tv_delete = myview.findViewById(R.id.tv_delete);
                myview.setTag(itemId);
                conditions.put(itemId, (RelativeLayout) myview);
                ll_askforleave_certainpicture.addView(myview); //动态添加图片
                tv_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("删除");
                        int itemId = (int) myview.getTag();
                        ll_askforleave_certainpicture.removeView(conditions.get(itemId));
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("查看 ");
                        imageView.setDrawingCacheEnabled(true);
                        MyImageDialog myImageDialog = null;
                        try {
                            myImageDialog = new MyImageDialog(AskForLeaveActivity.this, R.style.DialogStyle, 0, 0,
                                    MediaStore.Images.Media.getBitmap(AskForLeaveActivity.this.getContentResolver(), outputUri));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        myImageDialog.show();
                    }
                });
                Glide.with(AskForLeaveActivity.this).load(outputUri).into(imageView);
                itemId++;
            }
        }, false);//true裁剪，false不裁剪
    }

    //添加数据库
    private void addNote(String flow_note_id, String flow_no, String rankid,
                         String postitionid, String approve_pname, String approve_tel, String html_css, int PROCESS_COUNT) {
        Note note = new Note();
        note.setFlow_note_id(flow_note_id);
        note.setFlow_no(flow_no);
        note.setRankid(rankid);
        note.setArr_position_id(postitionid);
        note.setArr_pname(approve_pname);
        note.setArr_tel(approve_tel);
        note.setHtml_css(html_css);
        note.setPROCESS_COUNT(PROCESS_COUNT);
        notesBox.put(note);
        Log.d(TAG, "Inserted new note, ID: " + note.getId());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 2、在Activity中的onActivityResult()方法里与LQRPhotoSelectUtils关联
        mLqrPhotoSelectUtils.attachToActivityForResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 4) {
            arr_pname = data.getStringExtra("arr_pname");
            arr_strflow_note_id = data.getStringExtra("arr_strflow_note_id");
            arr_complayid = data.getStringExtra("arr_complayid");
            arr_departmentid = data.getStringExtra("arr_departmentid");
            arr_tdepartmentid = data.getStringExtra("arr_tdepartmentid");
            arr_position_id = data.getStringExtra("arr_position_id");
            arr_tel = data.getStringExtra("arr_tel");
            Log.d("onActivityResult", arr_pname + arr_strflow_note_id + ":" +
                    arr_complayid + ":" +
                    arr_departmentid + ":" +
                    arr_tdepartmentid + ":" +
                    arr_position_id + ":" +
                    arr_tel);
            Note note = notesBox.query().order(Note_.id).equal(Note_.html_css, "red").build().findFirst();
            if (note != null) {
                note.setArr_pname(arr_pname);
                note.setArr_complayid(arr_complayid);
                note.setArr_departmentid(arr_departmentid);
                note.setArr_tdepartmentid(arr_tdepartmentid);
                note.setArr_position_id(arr_position_id);
                note.setArr_tel(arr_tel);
                note.setHtml_css("green1");
                notesBox.put(note);
            }
            List<Note> note1 = notesBox.query().order(Note_.id).equal(Note_.html_css, "green").build().find();
            int myindex = data.getIntExtra("myindex", 0);
            Log.d("onActivityResultID", myindex + "");
            if (note1 != null && note1.size() > 0) {
                note1.get(myindex).setArr_pname(arr_pname);
                note1.get(myindex).setArr_complayid(arr_complayid);
                note1.get(myindex).setArr_departmentid(arr_departmentid);
                note1.get(myindex).setArr_tdepartmentid(arr_tdepartmentid);
                note1.get(myindex).setArr_position_id(arr_position_id);
                note1.get(myindex).setArr_tel(arr_tel);
                note1.get(myindex).setHtml_css("green1");
                notesBox.put(note1);
            }

            Iterator iter = conditionpeople.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                int key = (int) entry.getKey();
                int itemId = (int) conditionpeople.get(key).getTag();
                lv_person_contain.removeView(conditionpeople.get(itemId));
            }
            lv_person_contain.addView(infleatView(ids));
            Query<SubmitNote> submitNote1 = SubmitnotesBox.query().order(SubmitNote_.id).build();
            if (submitNote1.find().size() > 0) {
                for (int i = 0; i < submitNote1.find().size(); i++) {
                    if (!arr_pname.equals(submitNote1.find().get(i).getArr_pname())) {
                        //存要提交参数
                        SubmitNote submitNote = new SubmitNote();
                        submitNote.setArr_pname(arr_pname);
                        submitNote.setFlow_note_id(arr_strflow_note_id);
                        submitNote.setArr_complayid(arr_complayid);
                        submitNote.setArr_departmentid(arr_departmentid);
                        submitNote.setArr_tdepartmentid(arr_tdepartmentid);
                        submitNote.setArr_position_id(arr_position_id);
                        submitNote.setArr_tel(arr_tel);
                        submitNote.setHtml_css("green2");
                        SubmitnotesBox.put(submitNote);
                    }
                }
            } else {
                SubmitNote submitNote = new SubmitNote();
                submitNote.setArr_pname(arr_pname);
                submitNote.setFlow_note_id(arr_strflow_note_id);
                submitNote.setArr_complayid(arr_complayid);
                submitNote.setArr_departmentid(arr_departmentid);
                submitNote.setArr_tdepartmentid(arr_tdepartmentid);
                submitNote.setArr_position_id(arr_position_id);
                submitNote.setArr_tel(arr_tel);
                submitNote.setHtml_css("green2");
                SubmitnotesBox.put(submitNote);
            }
            for (int i = 0; i < submitNote1.find().size(); i++) {
                Log.d("要提交SubmitnotesBox", submitNote1.find().get(i).getArr_pname());
            }
        }
    }

    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
    private void takePhoto() {
        mLqrPhotoSelectUtils.takePhoto();
    }

    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void selectPhoto() {
        mLqrPhotoSelectUtils.selectPhoto();
    }

    @PermissionFail(requestCode = LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
    private void showTip1() {
        //        Toast.makeText(getApplicationContext(), "不给我权限是吧，那就别玩了", Toast.LENGTH_SHORT).show();
        showDialog();
    }

    @PermissionFail(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void showTip2() {
        //        Toast.makeText(getApplicationContext(), "不给我权限是吧，那就别玩了", Toast.LENGTH_SHORT).show();
        showDialog();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    //权限对话框
    public void showDialog() {
        //创建对话框创建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置对话框显示小图标
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置标题
        builder.setTitle("权限申请");
        //设置正文
        builder.setMessage("在设置-应用-盛大金禧-权限 中开启相机、存储权限，才能正常使用拍照或图片选择功能");

        //添加确定按钮点击事件
        builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {//点击完确定后，触发这个事件

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //这里用来跳到手机设置页，方便用户开启权限
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + AskForLeaveActivity.this.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        //添加取消按钮点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        //使用构建器创建出对话框对象
        AlertDialog dialog = builder.create();
        dialog.show();//显示对话框
    }

    @Override
    public void initListener() {
        super.initListener();
        rl_askforleave_days.setOnClickListener(this);
        ll_back.setOnClickListener(this);
        rl_askforleave_days.setOnClickListener(this);
        rl_start.setOnClickListener(this);
        rl_end.setOnClickListener(this);
        iv_askforleace_fujian.setOnClickListener(this);
        tv_askforleave_totaltime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG, "beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                LEAVEDAYS = tv_askforleave_totaltime.getText().toString();
                Log.d(TAG, "onTextChanged");
                if (!LEAVEDAYS.equals("") && !LEAVEDAYS.startsWith(".")) {
                    Iterator iter = conditionpeople.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry entry = (Map.Entry) iter.next();
                        int key = (int) entry.getKey();
                        int itemId = (int) conditionpeople.get(key).getTag();
                        lv_person_contain.removeView(conditionpeople.get(itemId));
                    }
                    if (Float.parseFloat(LEAVEDAYS) * 10 % 5 == 0) {
                        submitleave();
                    } else {
                        tv_askforleave_totaltime.setText("");
                        showToast("请假天数需要是0.5倍数");
                    }
                } else {
                    showToast("请假天数不能为空");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String cardNumber = AskForLeaveActivity.this.getResources().getStringArray(R.array.spingarr)[i];
                showToast(cardNumber);
                ASKFORLEAVETYPE = (i + 1) + "";
                LEAVEDAYS = tv_askforleave_totaltime.getText().toString();
                showToast(ASKFORLEAVETYPE);
                Log.d(TAG, "请假类型" + ASKFORLEAVETYPE);
                if (!tv_askforleave_totaltime.getText().toString().equals("")) {
                    Iterator iter = conditionpeople.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry entry = (Map.Entry) iter.next();
                        int key = (int) entry.getKey();
                        int itemId = (int) conditionpeople.get(key).getTag();
                        lv_person_contain.removeView(conditionpeople.get(itemId));
                    }
                    if (Float.parseFloat(LEAVEDAYS) * 10 % 5 == 0) {
                        submitleave();
                    } else {
                        showToast("请假天数需要是0.5倍数");
                    }
                } else {
                    showToast("请假天数不能为空");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_askforleave_start:
                setdatepick(tv_askforleave_starttime);
                break;
            case R.id.rl_askforleave_days:
                tv_askforleave_totaltime.setFocusable(true);
                tv_askforleave_totaltime.setFocusableInTouchMode(true);
                tv_askforleave_totaltime.requestFocus();
                AskForLeaveActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                break;
            case R.id.rl_askforleave_end:
                setdatepick(tv_askforleave_endtime);
                break;
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_askforleace_fujian:
                showToast("上传附件");
                ActionSheet.showSheet(this, this, null);
                break;
        }
    }

    //3.然后重写点击方法：
    @Override
    public void onClick(int whichButton) {
        switch (whichButton) {
            case ActionSheet.CHOOSE_PICTURE:
                //相册
                choosePic();
                break;
            case ActionSheet.TAKE_PICTURE:
                //拍照
                takePic();
                break;
            case ActionSheet.CANCEL:
                //取消
                break;
        }
    }

    //拍照
    public void takePic() {
        PermissionGen.with(AskForLeaveActivity.this)
                .addRequestCode(LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
                .permissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                ).request();
    }


    //相册
    public void choosePic() {
        PermissionGen.needPermission(AskForLeaveActivity.this,
                LQRPhotoSelectUtils.REQ_SELECT_PHOTO,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}
        );
    }

    //存储请假flow_note_id
    List<String> ids = new ArrayList<>();

    //请假接口5获取流程人
    private void submitleave() {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("ack", PreferenceUtil.getString("ack", ""));
        parmas.put("access_token", PreferenceUtil.getString("access_token", ""));
        parmas.put("dev_type", "1");
        parmas.put("username", PreferenceUtil.getString("username", ""));
        parmas.put("password", PreferenceUtil.getString("password", ""));
        parmas.put("company_id", PreferenceUtil.getString("company_id", ""));
        parmas.put("departmentid", PreferenceUtil.getString("department_id", ""));
        parmas.put("tdepartmentid", PreferenceUtil.getString("tdepartmentid", ""));
        parmas.put("rankid", PreferenceUtil.getString("rankid", ""));
        parmas.put("position_id", PreferenceUtil.getString("position", ""));
        parmas.put("flow_ptype", "A");
        parmas.put("leave_days", LEAVEDAYS);//申请的天数
        parmas.put("flow_type", ASKFORLEAVETYPE);//请假类型1.事假；2.病假；3.产假,4.陪护假 5.补休假；6.婚假；7.丧假，8其他
        HttpManager.postAsync(BaseUrl + "/oainterface/apply/choice_byleavedays_app.html?", parmas,
                new HttpManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        showToast("请检查网络设置");
                    }
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onResponse(String response) {
                        Log.d("请假", response.toString());
                        AskforleaveBean askforleaveBean = gson.fromJson(response, AskforleaveBean.class);
                        notesBox.removeAll();
                        ids.clear();
                        if (askforleaveBean.code != null && "1".equals(askforleaveBean.code)) {
                            PROCESS_COUNT = askforleaveBean.org.size();//记录流程路线数
                            for (int i = 0; i < PROCESS_COUNT; i++) {
                                for (int j = 0; j < askforleaveBean.org.get(i).info.size(); j++) {
                                    ids.add(askforleaveBean.org.get(i).info.get(j).flow_note_id);
                                    addNote(askforleaveBean.org.get(i).info.get(j).flow_note_id, askforleaveBean.org.get(i).info.get(j).flow_no,
                                            askforleaveBean.org.get(i).info.get(j).rankid, askforleaveBean.org.get(i).info.get(j).postitionid,
                                            askforleaveBean.org.get(i).info.get(j).approve_pname, askforleaveBean.org.get(i).info.get(j).approve_tel,
                                            askforleaveBean.org.get(i).info.get(j).html_css, PROCESS_COUNT);
                                }
                                for (int p = 0; p < notesQuery.find().size(); p++) {
                                    Log.d(TAG, "第" + p + "数据库" + notesQuery.find().get(p).getArr_pname());
                                }
                                lv_person_contain.addView(infleatView(ids));
                            }
                        } else {
                            showToast(askforleaveBean.errmsg);
                        }
                    }
                });
    }
    //提交
    public void SubmitAsk(View view) {
        Query<Note> note = notesBox.query().order(Note_.id).equal(Note_.html_css, "green").build();
        for (int i = 0; i < note.find().size(); i++) {
            Log.d("提交数据库", note.find().get(i).getArr_pname());
        }
        if (et_askforleave_reason.getText().toString().isEmpty()) {
            showToast("请假原因不能为空");
        } else if (tv_askforleave_totaltime.getText().toString().isEmpty()) {
            showToast("请假时长不能为空");
        } else if (!ischeck) {
            showToast("请选择流程");
        }  else if (Long.parseLong(DateUtils.dataOne(tv_askforleave_endtime.getText()+":00"))<Long.parseLong(DateUtils.dataOne(tv_askforleave_starttime.getText()+":00"))){
            showToast("请检查起止时间");
        } else {
            postAdd_leave();
//            postdemo();
        }
    }

    /**
     * @param id 职级号
     */
    //请假接口1，获取职位对应个人信息
    public void getfindInfoByTel(String id, List<String> floid, int index) {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("ack", PreferenceUtil.getString("ack", ""));
        parmas.put("access_token", PreferenceUtil.getString("access_token", ""));
        parmas.put("dev_type", "1");
        parmas.put("username", PreferenceUtil.getString("username", ""));
        parmas.put("password", PreferenceUtil.getString("password", ""));
        parmas.put("rankid", id);
        parmas.put("organ_tel", PreferenceUtil.getString("organ_tel", ""));
        HttpManager.postAsync(BaseUrl + "/oainterface/apply/findInfoByTel_inter.html?", parmas,
                new HttpManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onResponse(String response) {
                        Log.d("请假1", response.toString());
                        InfobytelBean infobytelBean = gson.fromJson(response, InfobytelBean.class);
                        if ("1".equals(infobytelBean.code)) {
                            Intent intent = new Intent(AskForLeaveActivity.this, MyContactsPersonDetalActivity.class);
                            intent.putExtra("mycontactstitleid1", response);
                            intent.putExtra("mycontactsflo", (String[]) floid.toArray(new String[floid.size()]));
                            intent.putExtra("myindex", index);
                            startActivityForResult(intent, 0);
                        } else if ("10023".equals(infobytelBean.errcode)) {
                            showToast(infobytelBean.errmsg);
                            Intent intent = new Intent(AskForLeaveActivity.this, AskConfirErrorPersonlActivity.class);
                            intent.putExtra("mycontactstitleid1", response);
                            intent.putExtra("mycontactsflo", (String[]) floid.toArray(new String[floid.size()]));
                            intent.putExtra("myindex", index);
                            startActivityForResult(intent, 0);
                        } else {
                            showToast(infobytelBean.errmsg);
                        }
                    }
                });
    }

    int persopnid = 0;
    int finalI1 = 0;

    //填充流程
    @SuppressLint("ResourceAsColor")
    public HorizontalScrollView infleatView(List<String> floid) {
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(AskForLeaveActivity.this);
        horizontalScrollView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        LinearLayout mylinearlayout = new LinearLayout(AskForLeaveActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER_VERTICAL;
        mylinearlayout.setLayoutParams(lp);
        mylinearlayout.setOrientation(LinearLayout.HORIZONTAL);
        mylinearlayout.setGravity(Gravity.CENTER_VERTICAL);
        mylinearlayout.setPadding(0, 8, 0, 0);
        for (int i = 0; i < notesQuery.find().size(); i++) {
            View mytextView = View.inflate(AskForLeaveActivity.this, R.layout.item_askforleave_person, null);
            mytextView.setTag(Integer.parseInt(notesQuery.find().get(i).getArr_position_id()));
            TextView tv_item_bank = mytextView.findViewById(R.id.tv_item_black);
            TextView tv_item_person = mytextView.findViewById(R.id.tv_item_person);
            tv_item_person.setTag(i);
            //隐藏过度点。。。
            if (i == 0) {
                tv_item_bank.setVisibility(View.GONE);
                CheckBox checkBox = new CheckBox(AskForLeaveActivity.this);
                checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        ischeck = b;
                    }
                });
                mylinearlayout.addView(checkBox);
            }
            tv_item_person.setText(notesQuery.find().get(i).getArr_pname());
            tv_item_person.setBackgroundResource(R.drawable.circle_askforleave);
            //区分点击事件
            if (notesQuery.find().get(i).getHtml_css().equals("red") ||
                    notesQuery.find().get(i).getHtml_css().equals("green") ||
                    notesQuery.find().get(i).getHtml_css().equals("green1")) {
                tv_item_person.setTextColor(R.color.red0);
                tv_item_person.setBackgroundResource(R.drawable.circle_askforleavered);
                finalI1 = i;
                mytextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("流程点击", notesQuery.find().get(finalI1).getRankid() + "：" + finalI1+":"+tv_item_person.getTag());
                        getfindInfoByTel(notesQuery.find().get(finalI1).getRankid(), floid, Integer.parseInt(tv_item_person.getTag().toString()));
                    }
                });
            } else {
                tv_item_person.setTextColor(R.color.colorPrimary);
                mytextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        showToast(tv_item_person.getText().toString() + (int) (mytextView.getTag()));
                        Intent intent = new Intent(AskForLeaveActivity.this, PersonInformationActivity.class);
                        intent.putExtra("PERSONIF", (int) (mytextView.getTag()) + "");
                        startActivity(intent);
                    }
                });
            }
            horizontalScrollView.setTag(persopnid);
            conditionpeople.put(persopnid, horizontalScrollView);
            mylinearlayout.addView(mytextView);
            persopnid++;
        }
        horizontalScrollView.addView(mylinearlayout);
        Log.d(TAG, "conditionpeople长度1" + conditionpeople.size());
        return horizontalScrollView;
    }

    //接口4上传多张图片
    public void postdemo() {
        MultiUpload.IuploadProgress iuploadProgress = new MultiUpload.IuploadProgress() {
            @Override
            public void uploadProgress(int progress) {
                // 进度条显示进度
            }

            @Override
            public void connectTimeOut() {

            }

            @Override
            public void uploadSuccess(String success) {
            }
        };
        List<BasicNameValuePair> texts = new ArrayList<BasicNameValuePair>();
        // 随便写了一些键名 键名 添加到数组
        texts.add(new BasicNameValuePair("ack", PreferenceUtil.getString("ack", "")));
        texts.add(new BasicNameValuePair("access_token", PreferenceUtil.getString("access_token", "")));
        texts.add(new BasicNameValuePair("dev_type", "1"));
        texts.add(new BasicNameValuePair("username", PreferenceUtil.getString("username", "")));
        texts.add(new BasicNameValuePair("password", PreferenceUtil.getString("password", "")));
        texts.add(new BasicNameValuePair("complayid", PreferenceUtil.getString("company_id", "")));
        texts.add(new BasicNameValuePair("departmentid", PreferenceUtil.getString("department_id", "")));
        texts.add(new BasicNameValuePair("tdepartmentid", PreferenceUtil.getString("tdepartmentid", "")));
        texts.add(new BasicNameValuePair("rankid", PreferenceUtil.getString("rankid", "")));
        texts.add(new BasicNameValuePair("position_id", PreferenceUtil.getString("position", "")));
        texts.add(new BasicNameValuePair("ntel", PreferenceUtil.getString("organ_tel", "")));
        texts.add(new BasicNameValuePair("flow_type", ASKFORLEAVETYPE));
        texts.add(new BasicNameValuePair("leave_days", LEAVEDAYS));
        texts.add(new BasicNameValuePair("flow_starttime", DateUtils.dataOne(tv_askforleave_starttime.getText() + ":00")));
        texts.add(new BasicNameValuePair("flow_endtime", DateUtils.dataOne(tv_askforleave_endtime.getText() + ":00")));
        texts.add(new BasicNameValuePair("reason", et_askforleave_reason.getText().toString()));
//        try {
//            texts.add(new BasicNameValuePair("pname", URLEncoder.encode(PreferenceUtil.getString("pname", ""), "UTF-8")));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        texts.add(new BasicNameValuePair("pname", PreferenceUtil.getString("pname", "")));
        Query<Note> note1 = notesBox.query().order(Note_.id).build();
        texts.add(new BasicNameValuePair("choice_flow_no", note1.find().get(0).getFlow_no()));
        Query<SubmitNote> submitnote = SubmitnotesBox.query().order(SubmitNote_.id).equal(SubmitNote_.html_css, "green2").build();
        if (submitnote.find().size() > 0) {
            for (int i = 0; i < submitnote.find().size(); i++) {
                Log.d("提交数据库postdemo", submitnote.find().get(i).getArr_pname() + ":" +
                        submitnote.find().get(i).getFlow_note_id() + ":" +
                        submitnote.find().get(i).getArr_complayid() + ":" +
                        submitnote.find().get(i).getArr_departmentid() + ":" +
                        submitnote.find().get(i).getArr_tdepartmentid() + ":" +
                        submitnote.find().get(i).getArr_position_id() + ":" +
                        submitnote.find().get(i).getArr_tel() + ":" +
                        submitnote.find().get(i).getFlow_no());
                list.add(submitnote.find().get(i).getFlow_note_id());
                list1.add(submitnote.find().get(i).getArr_complayid());
                list2.add(submitnote.find().get(i).getArr_departmentid());
                list3.add(submitnote.find().get(i).getArr_tdepartmentid());
                list4.add(submitnote.find().get(i).getArr_position_id());
                list5.add(submitnote.find().get(i).getArr_pname());
                list6.add(submitnote.find().get(i).getArr_tel());
            }
            texts.add(new BasicNameValuePair("arr_strflow_note_id", gson.toJson(list, List.class)));
            texts.add(new BasicNameValuePair("arr_complayid", gson.toJson(list1, List.class)));
            texts.add(new BasicNameValuePair("arr_departmentid", gson.toJson(list2, List.class)));
            texts.add(new BasicNameValuePair("arr_tdepartmentid", gson.toJson(list3, List.class)));
            texts.add(new BasicNameValuePair("arr_position_id", gson.toJson(list4, List.class)));
            texts.add(new BasicNameValuePair("arr_pname", gson.toJson(list5, List.class)));
            texts.add(new BasicNameValuePair("arr_tel", gson.toJson(list6, List.class)));
            Log.d("中文编码", gson.toJson(list5, List.class));
        }
        HashMap<File, String> files = new HashMap<File, String>();
        // 把内存卡的图片1.jpg 2.jpg 3.jpg  添加到数组
        //files.put(new File(Environment.getExternalStorageDirectory(),"1.jpg"), "image[]");
        //files.put(new File(Environment.getExternalStorageDirectory(),"2.jpg"), "image[]");
        //files.put(new File(Environment.getExternalStorageDirectory(),"3.jpg"), "image[]");
        // 把多图选择的图片 添加到数组
//        File file = new File("/mnt/sdcard/DCIM/Camera/IMG_20180308_150005.jpg");
//        File file1 = new File("/mnt/sdcard/DCIM/Camera/IMG_20180308_152658.jpg");
//        for (int i = 0; i < picturepatch.size(); i++) {
//            String[] ss = picturepatch.get(i).split("/");
//            String temp = "/mnt/sdcard/DCIM/Camera/" + ss[ss.length - 1];
//            Log.d("测试图片", temp);
//            File file2 = new File(temp);
//            files.put(file2, "typename[]");
//        }
//        files.put(file, "typename[]");
//        files.put(file1, "typename[]");
        for (int i = 0; i < Mypicturefilelist.size(); i++) {
            files.put(Mypicturefilelist.get(i), "typename[]");
        }
        new MultiUpload("http://192.168.1.199/oainterface/apply/add_leave", iuploadProgress).upload(texts, files, ASKFORLEAVETYPE, notesQuery);
//        new MultiUpload("http://192.168.1.199/oainterface/apply/insertimg_inter", iuploadProgress).upload(texts, files);
    }

    //接口4
    public void postAdd_leave() {
        Log.d("提交参数", "flow_type" + ASKFORLEAVETYPE + "LEAVEDAYS" + LEAVEDAYS + "flow_starttime" +
                tv_askforleave_starttime.getText().toString() +
                "flow_endtime" + tv_askforleave_starttime.getText().toString() + "reason" +
                et_askforleave_reason.getText().toString());
        ;
        //创建HttpClientUtil实例
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClientUtil httpClient = new HttpClientUtil();
                HttpClientUtil.MultipartForm form = httpClient.new MultipartForm();
                //设置form属性、参数
                form.setAction("http://192.168.1.199/oainterface/apply/add_leave");
                form.addNormalField("ack", PreferenceUtil.getString("ack", ""));
                form.addNormalField("access_token", PreferenceUtil.getString("access_token", ""));
                form.addNormalField("dev_type", "1");
                form.addNormalField("username", PreferenceUtil.getString("username", ""));
                form.addNormalField("password", PreferenceUtil.getString("password", ""));
                form.addNormalField("complayid", PreferenceUtil.getString("company_id", ""));
                form.addNormalField("departmentid", PreferenceUtil.getString("department_id", ""));
                form.addNormalField("tdepartmentid", PreferenceUtil.getString("tdepartmentid", ""));
                form.addNormalField("rankid", PreferenceUtil.getString("rankid", ""));
                form.addNormalField("position_id", PreferenceUtil.getString("position", ""));
                form.addNormalField("ntel", PreferenceUtil.getString("organ_tel", ""));
                form.addNormalField("flow_type", ASKFORLEAVETYPE);
                form.addNormalField("leave_days", LEAVEDAYS);
                form.addNormalField("flow_starttime", tv_askforleave_starttime.getText().toString());
                form.addNormalField("flow_endtime", tv_askforleave_endtime.getText().toString());
//                form.addNormalField("flow_starttime", "2018-3-20 18:29");
//                form.addNormalField("flow_endtime", "2018-3-20 18:29");
                form.addNormalField("reason", et_askforleave_reason.getText().toString());
                form.addNormalField("pname", PreferenceUtil.getString("pname", ""));
                Query<Note> note = notesBox.query().order(Note_.id).build();
                form.addNormalField("choice_flow_no", note.find().get(0).getFlow_no());
                Query<SubmitNote> submitnote = SubmitnotesBox.query().order(SubmitNote_.id).equal(SubmitNote_.html_css, "green2").build();
                if (submitnote.find().size() > 0) {
                    for (int i = 0; i < submitnote.find().size(); i++) {
                        Log.d("提交数据库postdemo", submitnote.find().get(i).getArr_pname() + ":" +
                                submitnote.find().get(i).getFlow_note_id() + ":" +
                                submitnote.find().get(i).getArr_complayid() + ":" +
                                submitnote.find().get(i).getArr_departmentid() + ":" +
                                submitnote.find().get(i).getArr_tdepartmentid() + ":" +
                                submitnote.find().get(i).getArr_position_id() + ":" +
                                submitnote.find().get(i).getArr_tel() + ":" +
                                submitnote.find().get(i).getFlow_no());
                        list.add(submitnote.find().get(i).getFlow_note_id());
                        list1.add(submitnote.find().get(i).getArr_complayid());
                        list2.add(submitnote.find().get(i).getArr_departmentid());
                        list3.add(submitnote.find().get(i).getArr_tdepartmentid());
                        list4.add(submitnote.find().get(i).getArr_position_id());
                        list5.add(submitnote.find().get(i).getArr_pname());
                        list6.add(submitnote.find().get(i).getArr_tel());
                    }
                    form.addNormalField("arr_strflow_note_id", gson.toJson(list, List.class));
                    form.addNormalField("arr_complayid", gson.toJson(list1, List.class));
                    form.addNormalField("arr_departmentid", gson.toJson(list2, List.class));
                    form.addNormalField("arr_tdepartmentid", gson.toJson(list3, List.class));
                    form.addNormalField("arr_position_id", gson.toJson(list4, List.class));
                    form.addNormalField("arr_pname", gson.toJson(list5, List.class));
                    form.addNormalField("arr_tel", gson.toJson(list6, List.class));
                }
                for (int i = 0; i < Mypicturefilelist.size(); i++) {
                    form.addFileField(Mypicturefilelist.get(i), "typename[]");
                }
                //提交表单返回参数
                String result = HttpClientUtil.submitForm(form);
//                Looper.prepare();
//                showToast("表单"+result );
//                Looper.loop();
//调用socket
//                Map map = new HashMap();
//                map.put("type", "loginphone");
//                map.put("ntel", loginBean.org.get(0).organ_tel);
//                String mapToJson = Gson.toJson(map);
//                MyApplication.mConnection.sendMessage(mapToJson);
//                Message msg = handler.obtainMessage(2, result);
//                handler.sendMessage(msg);
//                发送socket请求
                InterfacefourBean interfacefourBean = gson.fromJson(result, InterfacefourBean.class);
                if ("1".equals(interfacefourBean.code)) {
                    //调用socket
                    Map map = new HashMap();
                    map.put("type", "approvefrist");
                    map.put("flow_note_id", notesQuery.find().get(0).getFlow_note_id());
                    map.put("flow_no", notesQuery.find().get(0).getFlow_no());
                    map.put("ntel", PreferenceUtil.getString("organ_tel", ""));
                    map.put("to_ntel", notesQuery.find().get(0).getArr_tel());
                    map.put("flow_ptype", "A");
                    map.put("flow_type", ASKFORLEAVETYPE);
                    map.put("flow_ctype", "0");
                    map.put("apply_no", interfacefourBean.apply_no);
                    String mapToJson = gson.toJson(map);
                    MyApplication.mConnection.sendMessage(mapToJson);
                    finish();
                } else if ("10047".equals(interfacefourBean.errcode)) {
                    Looper.prepare();
                    Toast.makeText(MyApplication.getInstance(), "存在职级点，审批表无法初始化", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }

    /**
     * 设置时间选择器
     */
    DateTimePicker picker;

    public void setdatepick(TextView starttime) {
        picker = new DateTimePicker(AskForLeaveActivity.this, DateTimePicker.HOUR_24);
        String currenttime = DateUtils.getTodayDateTime();
        picker.setDateRangeStart(Integer.valueOf(currenttime.substring(0, 4)), Integer.valueOf(currenttime.substring(5, 7)), Integer.valueOf(currenttime.substring(8, 10)));
        picker.setDateRangeEnd(2025, 11, 11);
        picker.setTimeRangeStart(9, 0);
        picker.setTimeRangeEnd(20, 30);
        picker.setTopLineColor(0x99035499);
//                picker.setLabelTextColor(0xFFFF0000);
        picker.setDividerColor(0xFF035499);
        picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {

                starttime.setText(year + "-" + month + "-" + day + " " + hour + ":" + minute);
//                tv_askforleave_totaltime.setText(DateUtils.getTimeDifference(tv_askforleave_starttime.getText().toString(),tv_askforleave_endtime.getText().toString()));
            }
        });
        picker.show();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onResume() {
        super.onResume();
        showToast("onResume");
        for (int o = 0; o < notesQuery.find().size(); o++) {
            Log.d("onResume数据库", notesQuery.find().get(o).getArr_pname() == null ? "数据库为空" : notesQuery.find().get(o).getArr_pname());
        }
    }

    /**
     * 判断是否为中文字符
     */
    public boolean isChinese(String str) {
        if (str.getBytes().length == str.length()) {
            return false;
        }
        return true;
    }
}
