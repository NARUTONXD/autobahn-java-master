package io.crossbar.autobahn.sdjx.manager;

/**
 * Created by Administrator on 2018/3/8 0008.
 */

import android.util.Log;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * HttpClient工具类
 *
 * @author 曾繁添
 * @version 1.0
 */
public class HttpClientUtil {

    public final static String Method_POST = "POST";

    public final static String Method_GET = "GET";

    /**
     * multipart/form-data类型的表单提交
     *
     * @param form 表单数据
     */
    public static String submitForm(MultipartForm form) {

        // 返回字符串
        String responseStr = "";

        // 创建HttpClient实例
        HttpClient httpClient = new DefaultHttpClient();

        try {
            // 实例化提交请求
            HttpPost httpPost = new HttpPost(form.getAction());
            // 创建MultipartEntityBuilder
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            // 追加普通表单字段
            Map<String, String> normalFieldMap = form.getNormalField();
            for (Iterator<Entry<String, String>> iterator = normalFieldMap.entrySet().iterator(); iterator.hasNext(); ) {
                Entry<String, String> entity = iterator.next();
                entityBuilder.addPart(entity.getKey(), new StringBody(entity.getValue(), ContentType.create("text/plain", Consts.UTF_8)));
            }
            // 追加int表单字段
            Map<String, Integer> normalFieldintMap = form.getNormalintField();
            for (Iterator<Entry<String, Integer>> iterator = normalFieldintMap.entrySet().iterator(); iterator.hasNext(); ) {
                Entry<String, Integer> entity = iterator.next();
                entityBuilder.addPart(entity.getKey(), new StringBody(String.valueOf(entity.getValue()), ContentType.create("text/plain", Consts.UTF_8)));
            }
            // 追加文件字段
            Map<File, String> fileFieldMap = form.getFileField();
            if (fileFieldMap != null && fileFieldMap.size() > 0) {
                Set<Entry<File, String>> entrySet = fileFieldMap.entrySet();
                for (Entry<File, String> iterable_element : entrySet) {
                    entityBuilder.addBinaryBody(iterable_element.getValue(), iterable_element.getKey());
                    //create.addBinaryBody("111", iterable_element.getKey());
                    //create.addBinaryBody("albumFile", iterable_element.getKey(), ContentType.create("image/jpeg"), iterable_element.getValue());
                    Log.i("key", String.valueOf(iterable_element.getKey()));
                    Log.i("value", String.valueOf(iterable_element.getValue()));
                }
            }
            // 设置请求实体
            httpPost.setEntity(entityBuilder.build());
            // 发送请求
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            // 取得响应数据
//            ToastUtil.showToast(statusCode+"表单");
            HttpEntity resEntity = response.getEntity();
            if (200 == statusCode) {
                if (resEntity != null) {
                    responseStr = EntityUtils.toString(resEntity);
                }
            }
        } catch (Exception e) {
            System.out.println("提交表单失" + e.toString());
        } finally {

            httpClient.getConnectionManager().shutdown();
        }

        return responseStr;
    }

    /**
     * 表单字段Bean
     */
    public class MultipartForm implements Serializable {
        /**
         * 序列号
         */
        private static final long serialVersionUID = -2138044819190537198L;

        /**
         * 提交URL
         **/
        private String action = "";

        /**
         * 提交方式：POST/GET
         **/
        private String method = "POST";

        /**
         * 普通表单字段
         **/
        private Map<String, String> normalField = new LinkedHashMap<String, String>();
        /**
         * 普通int表单字段
         **/
        private Map<String, Integer> normalintField = new LinkedHashMap<String, Integer>();

        /**
         * 文件字段
         **/
        private Map<File, String> fileField = new LinkedHashMap<File, String>();
        //文件数组
        private List<Map> fileFieldlist = new ArrayList<>();
        /**
         * 上传的文件集合..........
         */
        private HashMap<File, String> files;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public Map<String, String> getNormalField() {
            return normalField;
        }
        public Map<String, Integer> getNormalintField() {
            return normalintField;
        }

        public void setNormalField(Map<String, String> normalField) {
            this.normalField = normalField;
        }
        public void setNormalintField(Map<String, Integer> normalField) {
            this.normalintField = normalField;
        }

        public Map<File, String> getFileField() {
            return fileField;
        }

        public void setFileField(Map<File, String> fileField) {
            this.fileField = fileField;
        }

        public void addFileField(File key, String value) {
            fileField.put(key, value);
        }

        public void addFileFieldmap(Map map) {
            fileFieldlist.add(map);
        }

        public void addNormalField(String key, String value) {
            normalField.put(key, value);
        }
        public void addNormalintField(String key, Integer value) {
            normalintField.put(key, value);
        }
    }
}
