package com.example.kaola.myapplication.network.model;

/**
 * @author zhangchao on 2019/3/5.
 */

public class TokenData {

    /**
     * code : 01
     * msg : 请求成功
     * data : {"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJfMVwiOjU4MDA1MDQ1MTM3OTAwNTQ0LFwiXzJcIjoxLFwiXzNcIjozfSJ9.6NsjgFnceNaPe_gEoqTbBL3QzA7YcM-7bSvq3RR1h4vCd1OdlaHObJclJ2Qtt21bsJIj12zWrx4jD97DjQucpQ"}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJfMVwiOjU4MDA1MDQ1MTM3OTAwNTQ0LFwiXzJcIjoxLFwiXzNcIjozfSJ9.6NsjgFnceNaPe_gEoqTbBL3QzA7YcM-7bSvq3RR1h4vCd1OdlaHObJclJ2Qtt21bsJIj12zWrx4jD97DjQucpQ
         */
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
