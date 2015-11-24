package com.example.wenlin.volley.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wenlin on 2015/11/5.
 */
public class Data implements Serializable {
    private static final long serialVersionUID = 1L;

    private Result result;

    public Result getResult(){
        return  result;
    }

    public static class Result  implements Serializable {
        private static final long serialVersionUID = 1L;

        private String offset;

        @SerializedName("limit")
        private String limit;
        private String count;
        private String sort;
        private List<ResultItem> results;

        public List<ResultItem> getResults() {
            return results;
        }

    }

    public static class ResultItem implements Serializable{
        private static final long serialVersionUID = 1L;

        private String _id;
        private String Name;
        private String Age;
        private String Sex ;
        private String Note;
        private String ImageName;

        public String get_id(){
            return _id;
        }

        public String getName(){
            if (Name==""){
                Name="未取名";
            }
            return Name;
        }

        public String getAge(){
            return Age;
        }

        public String getSex(){
            return Sex;
        }

        public String getNote(){
            return Note;
        }

        public String getImageName(){
            return  ImageName;
        }



    }

}


