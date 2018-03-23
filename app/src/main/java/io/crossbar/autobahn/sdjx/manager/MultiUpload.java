package io.crossbar.autobahn.sdjx.manager;

/**
 * Created by Administrator on 2018/3/8 0008.
 */

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import io.crossbar.autobahn.sdjx.MyApplication;
import io.crossbar.autobahn.sdjx.bean.InterfacefourBean;
import io.crossbar.autobahn.sdjx.db.Note;
import io.crossbar.autobahn.sdjx.db.SubmitNote;
import io.crossbar.autobahn.sdjx.db.SubmitNote_;
import io.crossbar.autobahn.sdjx.utils.PreferenceUtil;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;

public class MultiUpload {
//    //数据库
//    private Box<Note> notesBox;
//    private Query<Note> notesQuery;
    private Box<SubmitNote> SubmitnotesBox;
    private Query<SubmitNote> SubmitnotesQuery;
    /**
     * 请求的服务器地址
     */
    private String url;
    private Handler handler;
    private DefaultHttpClient defaultHttpClient;
    /**
     * 上传的文本集合...........
     */
    private List<BasicNameValuePair> texts;
    /**
     * 上传的文件集合..........
     */
    private HashMap<File, String> files;

    public MultiUpload(String url, final IuploadProgress iuploadProgress) {
        this.url = url;
        this.handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        // 上传进度...........
                        iuploadProgress.uploadProgress((Integer) msg.obj);
                        break;
                    case 1:
                        // 连接超时
                        iuploadProgress.connectTimeOut();
                        break;
                    case 2:
                        // 上传成功，看服务返回的结果...........
                        iuploadProgress.uploadSuccess((String) msg.obj);
                        break;
                    default:
                        break;
                }
            }
        };
        // 死机........
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("请求的服务器地址为空");
        }
    }

    /**
     * @param texts
     *            文本数据集合
     * @param files
     *            文件数据集合   因为上传文件的key有可能相同（name="files[]）,所以HashMap<File, String>
     *
     *
     *            <form method="post"
     *            action="http://192.168.2.145/wuxifu/test/uploadMulti.php"
     *            enctype="multipart/form-data"> <input type="file"
     *            name="files[]"><br/>
     *            <input type="file" name="files[]"><br/>
     *            <input type="file" name="files[]"><br/>
     *            <input type="text" name="name"></br/> <input type="text"
     *            name="age"></br/> <input type="submit" value="submit"> </form>
     */
    public void upload(List<BasicNameValuePair> texts, HashMap<File, String> files,String flow_type,Query<Note> notesQuery) {
        this.texts = texts;
        this.files = files;
        //获取数据库对象
        BoxStore boxStore = MyApplication.getInstance().getBoxStore();
//        notesBox = boxStore.boxFor(Note.class);
//        notesQuery = notesBox.query().order(Note_.id).build();
        SubmitnotesBox = boxStore.boxFor(SubmitNote.class);
        SubmitnotesQuery = SubmitnotesBox.query().order(SubmitNote_.id).build();

        new Thread() {
            public void run() {
                iniClient();
                HttpPost httpPost = iniHttpPost();
                try {
                    HttpResponse httpResponse = defaultHttpClient
                            .execute(httpPost);
                    if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        // 上传成功
                        closeConnect();
                        //由于我这边服务器编码为gbk，所以编码设置gbk，如果乱码就改为utf-8
                        String result = EntityUtils.toString(
                                httpResponse.getEntity(), "utf-8");
                        Log.e("上传成功........", result);
//                        ToastUtil.showToast("上传成功"+result);
                        Message msg = handler.obtainMessage(2, result);
                        handler.sendMessage(msg);
                        //发送socket请求
                        Gson gson=new Gson();
                        InterfacefourBean interfacefourBean=gson.fromJson(result,InterfacefourBean.class);
                        if ("1".equals(interfacefourBean.code)){
                            //调用socket
                            Map map = new HashMap();
                            map.put("type", "approvefrist");
                            map.put("flow_note_id", notesQuery.find().get(0).getFlow_note_id());
                            map.put("flow_no", notesQuery.find().get(0).getFlow_no());
                            map.put("ntel", PreferenceUtil.getString("organ_tel",""));
                            map.put("to_ntel", notesQuery.find().get(0).getArr_tel());
                            map.put("flow_ptype", "A");
                            map.put("flow_type", flow_type);
                            map.put("flow_ctype", "0");
                            map.put("apply_no", interfacefourBean.apply_no);
                            String mapToJson = gson.toJson(map);
                            MyApplication.mConnection.sendMessage(mapToJson);
                        }
                        else if ("10047".equals(interfacefourBean.errcode)){
                            Looper.prepare();
                            Toast.makeText(MyApplication.getInstance(),"存在职级点，审批表无法初始化",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                } catch (ClientProtocolException e) {
                    // e.printStackTrace();
                    handler.sendEmptyMessage(1);
                } catch (IOException e) {
                    // e.printStackTrace();
                    handler.sendEmptyMessage(1);
                }
            }

        }.start();
    }

    /**
     * 初始化post的内容
     *
     * @return  HttpPost
     */
    protected HttpPost iniHttpPost() {
        HttpPost httpPost = new HttpPost(url);
        // MultipartEntityBuilder
        MultipartEntityBuilder create = MultipartEntityBuilder.create();
        // 普通文本的发送，用户名&密码等
        if (texts != null && texts.size() > 0) {
            for (BasicNameValuePair iterable_element : texts) {

                create.addTextBody(iterable_element.getName(),
                        iterable_element.getValue());
                Log.i("key",iterable_element.getName());
                Log.i("value",iterable_element.getValue());
            }
        }
        // 二进制的发送，文件
        if (files != null && files.size() > 0) {
            Set<Entry<File, String>> entrySet = files.entrySet();
            for (Entry<File, String> iterable_element : entrySet) {
                create.addBinaryBody(iterable_element.getValue(), iterable_element.getKey());
                //create.addBinaryBody("111", iterable_element.getKey());
                //create.addBinaryBody("albumFile", iterable_element.getKey(), ContentType.create("image/jpeg"), iterable_element.getValue());
                Log.i("key", String.valueOf(iterable_element.getKey()));
                Log.i("value", String.valueOf(iterable_element.getValue()));
            }
        }

        HttpEntity httpEntity = create.build();
        // 上传内容的长度.............
        final long contentLength = httpEntity.getContentLength();
        ProgressOutHttpEntity progressOutHttpEntity = new ProgressOutHttpEntity(
                httpEntity, new ProgressOutHttpEntity.ProgressListener() {

            @Override
            public void transferred(long transferedBytes) {
                int currentProgress = (int) (100 * transferedBytes / contentLength);
                // 当前进度.............
                Message msg = handler.obtainMessage(0, currentProgress);
                handler.sendMessage(msg);
            }
        });
        // post内容的设置............
        httpPost.setEntity(progressOutHttpEntity);
        return httpPost;
    }

    /**
     * 初始化httpClient
     */
    private void iniClient() {
        if (defaultHttpClient == null) {
            defaultHttpClient = new DefaultHttpClient();
            // HTTP协议版本1.1
            defaultHttpClient.getParams().setParameter(
                    CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
            // 连接超时
            defaultHttpClient.getParams().setParameter(
                    CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
        }

    };
    /**
     * 关闭连接
     */
    private void closeConnect(){
        if(defaultHttpClient!=null){
            ClientConnectionManager connectionManager = defaultHttpClient.getConnectionManager();
            if(connectionManager!=null)
                connectionManager.shutdown();
            defaultHttpClient=null;
        }
    }

    public interface IuploadProgress {
        /**
         * 上传的进度
         *
         * @param progress
         *            （1-100）100就是上传完毕.......
         */
        public void uploadProgress(int progress);

        /**
         * @param success
         *            上传成功...........
         */
        public void uploadSuccess(String success);

        /**
         * 连接超时
         */
        public void connectTimeOut();
    }

}
