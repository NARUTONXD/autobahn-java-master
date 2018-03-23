package io.crossbar.autobahn.sdjx.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.activity.MainActivity;
import io.crossbar.autobahn.sdjx.activity.MyContactsDetalActivity;
import io.crossbar.autobahn.sdjx.activity.MyContactsPersonDetalActivity;
import io.crossbar.autobahn.sdjx.adapter.MyContactsListViewAdapter;
import io.crossbar.autobahn.sdjx.bean.ContactInfo;
import io.crossbar.autobahn.sdjx.bean.FirstContactBean;
import io.crossbar.autobahn.sdjx.manager.HttpManager;
import io.crossbar.autobahn.sdjx.receive.MyReceiver;
import io.crossbar.autobahn.sdjx.utils.PreferenceUtil;

import static io.crossbar.autobahn.sdjx.manager.HttpManager.BaseUrl;


/**
 * 通讯录主页
 */
public class ContactsFragment extends BaseFragment {
    static String TAG="ContactsFragment";
    MainActivity activity;
    private List<String> mStrs = new ArrayList<>();
    private SearchView mSearchView;
    //搜索内容的列表
    private ListView mListView;
    private ImageView imgExit;
    private TextView content_tv_title;
    private ImageView content_title_img;
    private LinearLayout ll_contacts;
    private TextView tv_contacts_totalcount;
    public static Gson gson;

    //联系人列表
    private ListView myContactsListView;

    //Model：部门的数据
    private List<ContactInfo> groups = new ArrayList<>();
    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}*/
    public static int departcount;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void init() {
    }
    //广播收到的消息
    private String broadcastmsg;
    MyReceiver myReceiver=new MyReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            broadcastmsg=intent.getStringExtra("msgsdjx");
            if (broadcastmsg.equals("{\"type\":\"whoislogintype\"}")){
                initData();
            }
        }
    };
    @Override
    public View initView() {
        gson = new Gson();
        View view = View.inflate(getActivity(), R.layout.fragment_contacts, null);
        //注册广播接收
        IntentFilter filter = new IntentFilter(Intent.ACTION_EDIT);
        getActivity().registerReceiver(myReceiver, filter);

        mSearchView = view.findViewById(R.id.searchView);
        mListView = view.findViewById(R.id.listView);
        imgExit = view.findViewById(R.id.imgExit);
        tv_contacts_totalcount = view.findViewById(R.id.tv_contacts_totalcount);
        myContactsListView = view.findViewById(R.id.expandableListView);
//         /* 隐藏垂直滚动条 */
//        myContactsListView.setVerticalScrollBarEnabled(false);
//        myContactsListView.setAdapter(new MyContactsListViewAdapter(groups, activity));
        myContactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MyContactsDetalActivity.class);
                intent.putExtra("mycontactstitle", groups.get(i).getName());
                intent.putExtra("mycontactstitleid", groups.get(i).getId()+"");
                Log.d(TAG,groups.get(i).getId()+"");
                startActivity(intent);
            }
        });
        ll_contacts = view.findViewById(R.id.ll_contacts);
        ll_contacts.setOnClickListener(view12 -> {
            InputMethodManager imm = (InputMethodManager)
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view12.getWindowToken(), 0);
        });
        content_tv_title = view.findViewById(R.id.content_tv_title);
        content_title_img = view.findViewById(R.id.content_title_img);
        imgExit.setVisibility(View.GONE);
        content_tv_title.setText("通讯录");
        content_title_img.setVisibility(View.GONE);
//        content_title_img.setImageResource(R.mipmap.icon_addfriends);
//        content_title_img.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), MyContactsActivity.class)));
        mListView.setAdapter(new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, mStrs));
        mListView.setTextFilterEnabled(true);
//        mHeaderView = View.inflate(getActivity(), R.layout.header_message_rv, null);
//        mHeaderView.setVisibility(View.GONE);
// 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
                    mListView.setVisibility(View.VISIBLE);
                    mListView.setFilterText(newText);
                } else {
                    mListView.setVisibility(View.GONE);
                    mListView.clearTextFilter();
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void initData() {
        getFirstContact();
    }

    //获取通讯录首页
    private void getFirstContact() {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("ack", PreferenceUtil.getString("ack", ""));
        parmas.put("access_token", PreferenceUtil.getString("access_token", ""));
        parmas.put("dev_type", "1");
        parmas.put("username", PreferenceUtil.getString("username", ""));
        parmas.put("password", PreferenceUtil.getString("password", ""));
        parmas.put("uid",  PreferenceUtil.getString("uid", ""));//个人登录ID(返回本人所在公司的所有部门)
        HttpManager.postAsync(BaseUrl + "/oainterface/Interfe/mail_index.html?", parmas,
                new HttpManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }


                    @Override
                    public void onResponse(String response) {
                        Log.d("getFirstContact", response.toString());
                        FirstContactBean firstContactBean = gson.fromJson(response, FirstContactBean.class);
                        if (firstContactBean.code == 1) {
                            groups.clear();
                            mStrs.clear();
                            tv_contacts_totalcount.setText(firstContactBean.msg.company.count_on_line + "/" + firstContactBean.msg.company.count);
                            departcount = firstContactBean.msg.department.size();
                            for (int i = 0; i < firstContactBean.msg.department.size(); i++) {
                                groups.add(new ContactInfo(firstContactBean.msg.department.get(i).dment_name, firstContactBean.msg.department.get(i).dment_lofficial,firstContactBean.msg.department.get(i).count, firstContactBean.msg.department.get(i).count_on_line, firstContactBean.msg.department.get(i).dment_id));
                                mStrs.add(groups.get(i).getName());
                            }
                            for (int i = 0; i < firstContactBean.msg.Subsidiary.size(); i++) {
                                groups.add(new ContactInfo(firstContactBean.msg.Subsidiary.get(i).company_name,"", firstContactBean.msg.Subsidiary.get(i).count, firstContactBean.msg.Subsidiary.get(i).count_on_line, firstContactBean.msg.Subsidiary.get(i).company_id));
                                mStrs.add(groups.get(i).getName());
                            }
                            myContactsListView.setAdapter(new MyContactsListViewAdapter(groups, activity));

                        } else {
                            showToast("网络异常");
                        }
                    }
                });
    }

    //获取通讯录二级
    private void getSecondContact(String name, String id) {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("ack", PreferenceUtil.getString("ack", ""));
        parmas.put("access_token", PreferenceUtil.getString("access_token", ""));
        parmas.put("dev_type", "1");
        parmas.put("username", PreferenceUtil.getString("username", ""));
        parmas.put("password", PreferenceUtil.getString("password", ""));
        parmas.put("uid", PreferenceUtil.getString("uid", ""));//个人登录ID(返回本人所在公司的所有部门)
        parmas.put("department_id", id);//个人登录ID(返回本人所在公司的所有部门)
        HttpManager.postAsync(BaseUrl + "/oainterface/Interfe/mail_department.html?", parmas,
                new HttpManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }
                    @Override
                    public void onResponse(String response) {
                        Log.d("getSecondContact1", response.toString());
                            FirstContactBean secondContactBean = gson.fromJson(response, FirstContactBean.class);
                            if (secondContactBean.code == 1&&secondContactBean.msg.department!=null) {
                                Intent intent = new Intent(activity, MyContactsDetalActivity.class);
                                intent.putExtra("mycontactstitle", name);
                                intent.putExtra("mycontactstitleinfo", response);
                                startActivity(intent);
                                Log.d("getSecondContact1", "secondContactBean执行");
                            } else {
                                showToast("无二级部门");
                                Intent intent = new Intent(activity, MyContactsPersonDetalActivity.class);
                                intent.putExtra("personinfo", response);
                                startActivity(intent);
                            }
                    }
                });
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(myReceiver);
        super.onDestroy();
    }
}