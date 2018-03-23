///////////////////////////////////////////////////////////////////////////////
//
//   AutobahnJava - http://crossbar.io/autobahn
//
//   Copyright (c) Crossbar.io Technologies GmbH and contributors
//
//   Licensed under the MIT License.
//   http://www.opensource.org/licenses/mit-license.php
//
///////////////////////////////////////////////////////////////////////////////

package io.crossbar.autobahn.sdjx.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AlphabetIndexer;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.adapter.MyContactserrorThreadListViewAdapter;
import io.crossbar.autobahn.sdjx.adapter.SearchContactAdapter;
import io.crossbar.autobahn.sdjx.adapter.SelectPersonAdapter;
import io.crossbar.autobahn.sdjx.bean.AskConfirBean;
import io.crossbar.autobahn.sdjx.bean.Contact;
import io.crossbar.autobahn.sdjx.bean.InfobytelBean;
import io.crossbar.autobahn.sdjx.manager.HttpManager;
import io.crossbar.autobahn.sdjx.utils.PinYin;
import io.crossbar.autobahn.sdjx.utils.PreferenceUtil;
import io.crossbar.autobahn.sdjx.widget.QuickIndexBar;

import static io.crossbar.autobahn.sdjx.manager.HttpManager.BaseUrl;

/**
 * 请假流程点联系人页面
 */
public class AskConfirErrorPersonlActivity extends BaseActivity {
    private TextView content_tv_title;
    private ImageView imgExit;
    private ImageView rightimg;
    private QuickIndexBar QuickIndexBar;
    private LinearLayout iv_mycontact_nouser;

    private ListView lv_contact;
    private TextView tv_center;
    private EditText et_search;
    private TextView tv_no_contact;
    /**
     * 定义字母表的排序规则
     */
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ#";
    private ArrayAdapter<Contact> searchAdapter;
    private List<Contact> list = new ArrayList<Contact>();
    public static final Uri URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private SectionIndexer mIndexer;
    private SearchTask searchTask;
    public static Gson gson = new Gson();
    //Model：部门的数据
    private List<AskConfirBean.MsgBean.StaffBean> groups = new ArrayList<AskConfirBean.MsgBean.StaffBean>();
    private List<InfobytelBean.OrgBean> groups1 = new ArrayList<InfobytelBean.OrgBean>();


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initView() {
        setContentView(R.layout.activity_mycontactspersondetal);
        content_tv_title = findViewById(R.id.content_tv_title);
        imgExit = findViewById(R.id.imgExit);
        rightimg = findViewById(R.id.content_title_img);
        rightimg.setVisibility(View.GONE);
        iv_mycontact_nouser = findViewById(R.id.iv_mycontact_nouser);
        QuickIndexBar = findViewById(R.id.qib);
        imgExit.setImageResource(R.mipmap.ic_back);
        list.clear();
        lv_contact = (ListView) findViewById(R.id.lv_contact);
        tv_center = (TextView) findViewById(R.id.tv_center);
        et_search = (EditText) findViewById(R.id.et_search);
        et_search.setVisibility(View.GONE);
        tv_no_contact = (TextView) findViewById(R.id.tv_no_contact);
        //请假页面跳转过来
        getthread(getIntent().getStringExtra("mycontactstitleid1"));
        searchAdapter = new SearchContactAdapter(this,
                R.layout.list_item, filterList);
    }

    @Override
    public void initListener() {
        imgExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        QuickIndexBar.setOnIndexChangeListener(new QuickIndexBar.OnIndexChangeListener() {
            @Override
            public void onIndexChange(int section) {
            }

            @Override
            public void onActionUp() {
            }
        });
        QuickIndexBar.setOnIndexChangeListener(new QuickIndexBar.OnIndexChangeListener() {
            @Override
            public void onIndexChange(int section) {
                int position = mIndexer.getPositionForSection(section);
                lv_contact.setSelection(position);
                tv_center.setText(QuickIndexBar.INDEX_ARRAYS[section]);
                tv_center.setVisibility(View.VISIBLE);
            }

            @Override
            public void onActionUp() {
                tv_center.setVisibility(View.GONE);
            }
        });
        // 文本编辑框内容改变时
        et_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchString = s.toString().trim()
                        .toLowerCase(Locale.US);
                if (searchTask != null
                        && searchTask.getStatus() != AsyncTask.Status.FINISHED) {
                    // 若之前有task，先取消搜索task
                    searchTask.cancel(true);
                }
                // 开启搜索task
                searchTask = new SearchTask();
                searchTask.execute(searchString);
            }
        });
    }

    //获取三级联系人数据
    public void getthread(String id) {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("ack", PreferenceUtil.getString("ack", ""));
        parmas.put("access_token", PreferenceUtil.getString("access_token", ""));
        parmas.put("dev_type", "1");
        parmas.put("username", PreferenceUtil.getString("username", ""));
        parmas.put("password", PreferenceUtil.getString("password", ""));
        parmas.put("uid", PreferenceUtil.getString("uid", ""));//个人登录ID(返回本人所在公司的所有部门)
        parmas.put("department_id", id);//个人登录ID(返回本人所在公司的所有部门)
        HttpManager.postAsync(BaseUrl + "/oainterface/Interfe/mail_staff.html?", parmas,
                new HttpManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        AskConfirBean threadContactBean = gson.fromJson(response, AskConfirBean.class);
                        content_tv_title.setText("选择审核人");
                        groups.clear();
                        if (threadContactBean.code == 1) {
                            for (int i = 0; i < threadContactBean.msg.staff.size(); i++) {
                                groups.add(threadContactBean.msg.staff.get(i));
                            }
                            lv_contact.setAdapter(new MyContactserrorThreadListViewAdapter(groups, AskConfirErrorPersonlActivity.this));
                            lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent = new Intent();
                                    intent.putExtra("arr_pname", groups.get(i).organ_name);
                                    intent.putExtra("arr_strflow_note_id", getIntent().getStringArrayExtra("mycontactsflo")[i].toString());
                                    Log.d("mycontactsflo", getIntent().getStringArrayExtra("mycontactsflo")[i].toString());
                                    intent.putExtra("arr_complayid", groups.get(i).organ_company);
                                    intent.putExtra("arr_departmentid", groups.get(i).organ_department);
                                    intent.putExtra("arr_tdepartmentid", groups.get(i).organ_tdepartment);
                                    intent.putExtra("arr_position_id", groups.get(i).organ_position);
                                    intent.putExtra("arr_tel", groups.get(i).organ_tel);
                                    intent.putExtra("myindex", getIntent().getIntExtra("myindex", 0));
                                    setResult(4, intent);
                                    finish();
                                }
                            });
                            if (groups.size() > 0) {
                                iv_mycontact_nouser.setVisibility(View.GONE);
                                QuickIndexBar.setVisibility(View.GONE);
                            } else {
                                iv_mycontact_nouser.setVisibility(View.VISIBLE);
                                QuickIndexBar.setVisibility(View.GONE);
                            }
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // 搜索出的联系人列表
    List<Contact> filterList = new ArrayList<Contact>();
    // 是否为搜索模式
    boolean searchMode;

    class SearchTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            filterList.clear();
            searchMode = params[0].length() > 0;
            if (searchMode) {
                for (Contact contact : list) {
                    boolean isChinese = contact.getName()
                            .toLowerCase(Locale.US).indexOf(params[0]) > -1;
                    boolean isPinyin = contact.getPinyinName().indexOf(
                            params[0]) > -1;
                    if (isPinyin || isChinese) {
                        filterList.add(contact);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (searchMode) {
                if (filterList.size() > 0) {
                    lv_contact.setAdapter(searchAdapter);
                } else {
                    lv_contact.setVisibility(View.GONE);
                    tv_no_contact.setVisibility(View.VISIBLE);
                }
                QuickIndexBar.setVisibility(View.GONE);
            } else {
                tv_no_contact.setVisibility(View.GONE);
                lv_contact.setVisibility(View.VISIBLE);
//                lv_contact.setAdapter(adapter);
                QuickIndexBar.setVisibility(View.VISIBLE);
            }
        }

    }

    private List<Contact> getContactArray() {
        Cursor cursor = getContentResolver().query(URI,
                new String[]{"display_name", "sort_key", "phonebook_label"},
                null, null, "phonebook_label");
        Contact contact;
        if (cursor.moveToFirst()) {
            do {
                contact = new Contact();
                String contact_name = cursor.getString(0);
                String phonebook_label = cursor.getString(2);
                contact.setPhonebookLabel(getPhonebookLabel(phonebook_label));
                contact.setPinyinName(PinYin.getPinYin(contact_name));
                contact.setName(contact_name);
                list.add(contact);
            } while (cursor.moveToNext());
        }
        // 实例化indexer
        mIndexer = new AlphabetIndexer(cursor, 2, alphabet);
        return list;
    }

    private String getPhonebookLabel(String phonebook_label) {
        if (phonebook_label.matches("[A-Z]")) {
            return phonebook_label;
        }
        return "#";
    }


    public void getData() {
        InfobytelBean infobytelBean = gson.fromJson(getIntent().getStringExtra("mycontactstitleid1"), InfobytelBean.class);
        content_tv_title.setText("选择审核人");
        groups1.clear();
        if ("1".equals(infobytelBean.code)) {
            for (int i = 0; i < infobytelBean.org.size(); i++) {
                groups1.add(infobytelBean.org.get(i));
            }
            lv_contact.setAdapter(new SelectPersonAdapter(groups1, AskConfirErrorPersonlActivity.this));
            lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent();
                    intent.putExtra("arr_pname", groups1.get(i).organ_name);
                    intent.putExtra("arr_strflow_note_id", getIntent().getStringArrayExtra("mycontactsflo")[i].toString());
                    Log.d("mycontactsflo", getIntent().getStringArrayExtra("mycontactsflo")[i].toString());
                    intent.putExtra("arr_complayid", groups1.get(i).organ_company);
                    intent.putExtra("arr_departmentid", groups1.get(i).organ_department);
                    intent.putExtra("arr_tdepartmentid", groups1.get(i).organ_tdepartment);
                    intent.putExtra("arr_position_id", groups1.get(i).organ_position);
                    intent.putExtra("arr_tel", groups1.get(i).organ_tel);
                    intent.putExtra("myindex", getIntent().getIntExtra("myindex", 0));
                    setResult(4, intent);
                    finish();
                }
            });
            if (groups1.size() > 0) {
                iv_mycontact_nouser.setVisibility(View.GONE);
                QuickIndexBar.setVisibility(View.GONE);
            } else {
                iv_mycontact_nouser.setVisibility(View.VISIBLE);
                QuickIndexBar.setVisibility(View.GONE);
            }
        }
    }

}
