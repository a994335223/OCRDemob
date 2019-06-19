package com.baidu.ocr.demo;

import java.util.List;

public class GsRzData {

    @Override
    public String toString() {
        return "GsRzData{" +
                "request_id='" + request_id + '\'' +
                ", success=" + success +
                ", result=" + result +
                '}';
    }

    /**
     * request_id : 20190524220453_8f4f19e77fa39952f92ec9d27e9a765a
     * result : [{"roi":{"height":96,"left":249,"top":372,"width":101},"text":{"content":"阆中市鸿鑫竹鼠养殖场","prob":0.46305596828460693}}]
     * success : true
     */

    private String request_id;
    private boolean success;
    private List<ResultBean> result;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        @Override
        public String toString() {
            return "ResultBean{" +
                    "roi=" + roi +
                    ", text=" + text +
                    '}';
        }

        /**
         * roi : {"height":96,"left":249,"top":372,"width":101}
         * text : {"content":"阆中市鸿鑫竹鼠养殖场","prob":0.46305596828460693}
         */

        private RoiBean roi;
        private TextBean text;

        public RoiBean getRoi() {
            return roi;
        }

        public void setRoi(RoiBean roi) {
            this.roi = roi;
        }

        public TextBean getText() {
            return text;
        }

        public void setText(TextBean text) {
            this.text = text;
        }

        public static class RoiBean {
            /**
             * height : 96
             * left : 249
             * top : 372
             * width : 101
             */

            private int height;
            private int left;
            private int top;
            private int width;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }
        }

        public static class TextBean {
            @Override
            public String toString() {
                return "TextBean{" +
                        "content='" + content + '\'' +
                        ", prob=" + prob +
                        '}';
            }

            /**
             * content : 阆中市鸿鑫竹鼠养殖场
             * prob : 0.46305596828460693
             */

            private String content;
            private double prob;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public double getProb() {
                return prob;
            }

            public void setProb(double prob) {
                this.prob = prob;
            }
        }
    }
}
