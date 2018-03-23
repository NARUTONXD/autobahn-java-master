package io.crossbar.autobahn.sdjx.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.codbking.calendar.CaledarAdapter;
import com.codbking.calendar.CalendarBean;
import com.codbking.calendar.CalendarDateView;
import com.codbking.calendar.CalendarUtil;
import com.codbking.calendar.CalendarView;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.crossbar.autobahn.sdjx.MyApplication;
import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.activity.MainActivity;
import io.crossbar.autobahn.sdjx.activity.SalaryActivity;
import io.crossbar.autobahn.sdjx.manager.HttpManager;
import io.crossbar.autobahn.sdjx.utils.PreferenceUtil;

import static io.crossbar.autobahn.sdjx.manager.HttpManager.BaseUrl;


/**
 * 工作首页
 */
public class WorkFragment extends BaseFragment {

    MainActivity activity;
    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;
    private ImageView imgExit;
    private TextView content_tv_title;
    private ImageView content_title_img;
    private CalendarDateView mCalendarDateView;
    private TextView mTitle;
    private ListView mList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void init() {

    }

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_work, null);
//        mHeaderView = View.inflate(getActivity(), R.layout.header_message_rv, null);
//        mHeaderView.setVisibility(View.GONE);
        gridView = view.findViewById(R.id.gridview);
        imgExit = view.findViewById(R.id.imgExit);
        content_tv_title = view.findViewById(R.id.content_tv_title);
        content_title_img = view.findViewById(R.id.content_title_img);
        mTitle = view.findViewById(R.id.title);
        mList = view.findViewById(R.id.list);
        mCalendarDateView = view.findViewById(R.id.calendarDateView);
        mCalendarDateView.setAdapter(new CaledarAdapter() {
            @SuppressLint("ResourceAsColor")
            @Override
            public View getView(View convertView, ViewGroup parentView, CalendarBean bean) {
                TextView view;
                if (convertView == null) {
                    convertView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.item_calendar, null);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(px(48), px(48));
                    convertView.setLayoutParams(params);
                }

                view = (TextView) convertView.findViewById(R.id.text);

                view.setText("" + bean.day);
                if (bean.mothFlag != 0) {
                    view.setTextColor(R.color.red0);
                } else {
                    view.setTextColor(R.color.black);
                }

                return convertView;
            }
        });

        mCalendarDateView.setOnItemClickListener(new CalendarView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion, CalendarBean bean) {
                mTitle.setText(bean.year + "/" + getDisPlayNumber(bean.moth) + "/" + getDisPlayNumber(bean.day));
            }
        });

        int[] data = CalendarUtil.getYMD(new Date());
        mTitle.setText(data[0] + "/" + data[1] + "/" + data[2]);

        mList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(activity).inflate(android.R.layout.simple_list_item_1, null);
                }

                TextView textView = (TextView) convertView;
                textView.setText("item" + position);

                return convertView;
            }
        });
        return view;
    }

    public static int px(float dipValue) {
        Resources r = Resources.getSystem();
        final float scale = r.getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private String getDisPlayNumber(int num) {
        return num < 10 ? "0" + num : "" + num;
    }

    @Override
    public void initData() {
        //图标
        int icno[] = {R.mipmap.icon_doc, R.mipmap.icon_told, R.mipmap.icon_salary,
                R.mipmap.icon_asset, R.mipmap.icon_teamwork, R.mipmap.icon_progresser};
        //图标下的文字
        String name[] = {"公文", "公告", "工资条", "资产管理", "协同", "流程申请"};
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icno.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icno[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }
        String[] from = {"img", "text"};

        int[] to = {R.id.img, R.id.text};

        adapter = new SimpleAdapter(activity, dataList, R.layout.gridview_item, from, to);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2==2){
                    Intent intent=new Intent(getActivity(), SalaryActivity.class);
                    startActivity(intent);
                }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("提示").setMessage(dataList.get(arg2).get("text").toString()).create().show();}
            }
        });
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        setAdapter();
//    }

    //测试
    private void ceshi() {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("ack", PreferenceUtil.getString("ack", ""));
        parmas.put("access_token", PreferenceUtil.getString("access_token", ""));
        parmas.put("dev_type", MyApplication.LOGINTYPE);//设备类型
        parmas.put("username", "18229863760");//设备类型
        parmas.put("password", "123456");//设备类型
        parmas.put("rankid", "1");
        HttpManager.postAsync(BaseUrl + "/oainterface/apply/findInfoByTel_inter.html?", parmas,
                new HttpManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("登录login", response.toString());
                    }
                });
    }


}
