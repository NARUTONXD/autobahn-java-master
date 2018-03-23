package io.crossbar.autobahn.sdjx.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/30 0030.
 */

public class MessageBean {

    /**
     * type : storeMsg
     * info : [{"val":"He, world!sss","ntel":"","to_ntel":"18898730808","store_msg_sendtime":1517291583,"msg_arr":{"store_msg_id":108,"store_msg_ntel":"","store_msg_to_ntel":"18898730808","flow_no":"0","flow_ptype":"A","flow_type":0,"flow_ctype":0,"apply_no":"0","store_msg_val":"He, world!sss","store_msg_sendtime":1517291583,"store_msg_readtime":0,"store_msg_type":0}},{"val":"Hello, world!sss","ntel":"","to_ntel":"18898730808","store_msg_sendtime":1517291579,"msg_arr":{"store_msg_id":107,"store_msg_ntel":"","store_msg_to_ntel":"18898730808","flow_no":"0","flow_ptype":"A","flow_type":0,"flow_ctype":0,"apply_no":"0","store_msg_val":"Hello, world!sss","store_msg_sendtime":1517291579,"store_msg_readtime":0,"store_msg_type":0}},{"val":"526","ntel":"","to_ntel":"18898730808","store_msg_sendtime":1517290914,"msg_arr":{"store_msg_id":104,"store_msg_ntel":"","store_msg_to_ntel":"18898730808","flow_no":"0","flow_ptype":"A","flow_type":0,"flow_ctype":0,"apply_no":"0","store_msg_val":"526","store_msg_sendtime":1517290914,"store_msg_readtime":0,"store_msg_type":0}},{"val":"123!","ntel":"","to_ntel":"18898730808","store_msg_sendtime":1517290824,"msg_arr":{"store_msg_id":97,"store_msg_ntel":"","store_msg_to_ntel":"18898730808","flow_no":"0","flow_ptype":"A","flow_type":0,"flow_ctype":0,"apply_no":"0","store_msg_val":"123!","store_msg_sendtime":1517290824,"store_msg_readtime":0,"store_msg_type":0}},{"val":"123!","ntel":"","to_ntel":"18898730808","store_msg_sendtime":1517290806,"msg_arr":{"store_msg_id":96,"store_msg_ntel":"","store_msg_to_ntel":"18898730808","flow_no":"0","flow_ptype":"A","flow_type":0,"flow_ctype":0,"apply_no":"0","store_msg_val":"123!","store_msg_sendtime":1517290806,"store_msg_readtime":0,"store_msg_type":0}},{"val":"离线消息同时发送两人","ntel":"18773315923","to_ntel":"18898730808","store_msg_sendtime":1516350650,"msg_arr":{"store_msg_id":18,"store_msg_ntel":"18773315923","store_msg_to_ntel":"18898730808","flow_no":"0","flow_ptype":"A","flow_type":0,"flow_ctype":0,"apply_no":"0","store_msg_val":"离线消息同时发送两人","store_msg_sendtime":1516350650,"store_msg_readtime":0,"store_msg_type":0}}]
     */

    private String type;
    private List<InfoBean> info;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * val : He, world!sss
         * ntel :
         * to_ntel : 18898730808
         * store_msg_sendtime : 1517291583
         * msg_arr : {"store_msg_id":108,"store_msg_ntel":"","store_msg_to_ntel":"18898730808","flow_no":"0","flow_ptype":"A","flow_type":0,"flow_ctype":0,"apply_no":"0","store_msg_val":"He, world!sss","store_msg_sendtime":1517291583,"store_msg_readtime":0,"store_msg_type":0}
         */

        private String val;
        private String ntel;
        private String to_ntel;
        private int store_msg_sendtime;
        private MsgArrBean msg_arr;

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public String getNtel() {
            return ntel;
        }

        public void setNtel(String ntel) {
            this.ntel = ntel;
        }

        public String getTo_ntel() {
            return to_ntel;
        }

        public void setTo_ntel(String to_ntel) {
            this.to_ntel = to_ntel;
        }

        public int getStore_msg_sendtime() {
            return store_msg_sendtime;
        }

        public void setStore_msg_sendtime(int store_msg_sendtime) {
            this.store_msg_sendtime = store_msg_sendtime;
        }

        public MsgArrBean getMsg_arr() {
            return msg_arr;
        }

        public void setMsg_arr(MsgArrBean msg_arr) {
            this.msg_arr = msg_arr;
        }

        public static class MsgArrBean {
            /**
             * store_msg_id : 108
             * store_msg_ntel :
             * store_msg_to_ntel : 18898730808
             * flow_no : 0
             * flow_ptype : A
             * flow_type : 0
             * flow_ctype : 0
             * apply_no : 0
             * store_msg_val : He, world!sss
             * store_msg_sendtime : 1517291583
             * store_msg_readtime : 0
             * store_msg_type : 0
             */

            private int store_msg_id;
            private String store_msg_ntel;
            private String store_msg_to_ntel;
            private String flow_no;
            private String flow_ptype;
            private int flow_type;
            private int flow_ctype;
            private String apply_no;
            private String store_msg_val;
            private int store_msg_sendtime;
            private int store_msg_readtime;
            private int store_msg_type;

            public int getStore_msg_id() {
                return store_msg_id;
            }

            public void setStore_msg_id(int store_msg_id) {
                this.store_msg_id = store_msg_id;
            }

            public String getStore_msg_ntel() {
                return store_msg_ntel;
            }

            public void setStore_msg_ntel(String store_msg_ntel) {
                this.store_msg_ntel = store_msg_ntel;
            }

            public String getStore_msg_to_ntel() {
                return store_msg_to_ntel;
            }

            public void setStore_msg_to_ntel(String store_msg_to_ntel) {
                this.store_msg_to_ntel = store_msg_to_ntel;
            }

            public String getFlow_no() {
                return flow_no;
            }

            public void setFlow_no(String flow_no) {
                this.flow_no = flow_no;
            }

            public String getFlow_ptype() {
                return flow_ptype;
            }

            public void setFlow_ptype(String flow_ptype) {
                this.flow_ptype = flow_ptype;
            }

            public int getFlow_type() {
                return flow_type;
            }

            public void setFlow_type(int flow_type) {
                this.flow_type = flow_type;
            }

            public int getFlow_ctype() {
                return flow_ctype;
            }

            public void setFlow_ctype(int flow_ctype) {
                this.flow_ctype = flow_ctype;
            }

            public String getApply_no() {
                return apply_no;
            }

            public void setApply_no(String apply_no) {
                this.apply_no = apply_no;
            }

            public String getStore_msg_val() {
                return store_msg_val;
            }

            public void setStore_msg_val(String store_msg_val) {
                this.store_msg_val = store_msg_val;
            }

            public int getStore_msg_sendtime() {
                return store_msg_sendtime;
            }

            public void setStore_msg_sendtime(int store_msg_sendtime) {
                this.store_msg_sendtime = store_msg_sendtime;
            }

            public int getStore_msg_readtime() {
                return store_msg_readtime;
            }

            public void setStore_msg_readtime(int store_msg_readtime) {
                this.store_msg_readtime = store_msg_readtime;
            }

            public int getStore_msg_type() {
                return store_msg_type;
            }

            public void setStore_msg_type(int store_msg_type) {
                this.store_msg_type = store_msg_type;
            }
        }
    }
}
