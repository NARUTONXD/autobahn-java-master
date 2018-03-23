package io.crossbar.autobahn.sdjx.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.crossbar.autobahn.sdjx.R;
import io.crossbar.autobahn.sdjx.activity.AskForLeaveActivity;
import io.crossbar.autobahn.sdjx.activity.MainActivity;
import io.crossbar.autobahn.sdjx.activity.PurchaseActivity;


/**
 * 流程首页
 */
public class ProgressFragment extends BaseFragment {

    private List<Map<String, Object>> dataList;
    MainActivity activity;
    private ImageView imgExit;
    private TextView content_tv_title;
    private ImageView content_title_img;
    private SimpleAdapter adapter;
    private GridView gridView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void init() {
    }

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_progress, null);
        imgExit = view.findViewById(R.id.imgExit);
        gridView = view.findViewById(R.id.gridview);
        content_tv_title = view.findViewById(R.id.content_tv_title);
        content_title_img = view.findViewById(R.id.content_title_img);
        imgExit.setVisibility(View.GONE);
        content_title_img.setVisibility(View.GONE);
        imgExit.setVisibility(View.GONE);
        content_tv_title.setText("流程");
        return view;
    }

    @Override
    public void initData() {
//图标
        int icno[] = {R.mipmap.ic_progress_leave, R.mipmap.ic_progress_gowork, R.mipmap.ic_progress_workout,
                R.mipmap.ic_progress_exwork, R.mipmap.ic_progress_borrow, R.mipmap.ic_progress_reimbursement,
                R.mipmap.ic_progress_pay, R.mipmap.ic_progress_buy, R.mipmap.ic_progress_usecar, R.mipmap.ic_progress_speclaltime};
        //图标下的文字
        String name[] = {"请假", "出差", "外出", "加班", "借支", "报销", "付款申请", "物品采购", "用车申请", "特殊事项"};
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icno.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icno[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }
        String[] from = {"img", "text"};

        int[] to = {R.id.img, R.id.text};

        adapter = new SimpleAdapter(activity, dataList, R.layout.progressfragment_gridview_item, from, to);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                switch (arg2) {
                    case 0:
                        Intent intent = new Intent(getActivity(), AskForLeaveActivity.class);
                        startActivity(intent);
                        break;
                    case 7://采购
                        Intent intent1 = new Intent(getActivity(), PurchaseActivity.class);
                        startActivity(intent1);
                        break;
                    default:
                        builder.setTitle("提示").setMessage(dataList.get(arg2).get("text").toString()).create().show();
                        break;
                }
//                if (arg2==0){
//
//                }else {
//                    builder.setTitle("提示").setMessage(dataList.get(arg2).get("text").toString()).create().show();
//                }
            }
        });
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        setAdapter();
//    }

    private void setAdapter() {
    }


}
