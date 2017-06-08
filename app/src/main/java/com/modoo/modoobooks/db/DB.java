package com.modoo.modoobooks.db;

import android.os.AsyncTask;
import android.util.Log;

import com.modoo.modoobooks.JoinActivity;
import com.modoo.modoobooks.LoginActivity;
import com.modoo.modoobooks.MainActivity;
import com.modoo.modoobooks.MyInfoActivity;
import com.modoo.modoobooks.SearchActivity;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by YOONGOO on 2017-06-07.
 */

public class DB {
    final static String LOG_TAG = DB.class.getSimpleName();


    // 회원 로그인 기능 (modoo_login)
    public static class modoo_login extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String user_id = params[0];
            String user_pw = params[1];

            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()
                    .add("user_id", user_id)
                    .add("user_pw", user_pw)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_LOGIN)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();

                Log.d(LOG_TAG, returnValue);

                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            if(paramValue.equals("1")){
                LoginActivity.loginSucceed = true;
            }
            else if(paramValue.equals("0")){
                LoginActivity.loginSucceed = false;
            }
        }
    }

    // 회원가입 기능 (modoo_join)
    public static class modoo_join extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String user_id = params[0];
            String user_pw = params[1];
            String user_name = params[2];
            String user_addr = params[3];
            String user_phone = params[4];
            String user_email = params[5];
            String user_gender = params[6];

            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()
                    .add("user_id", user_id)
                    .add("user_pw", user_pw)
                    .add("user_name", user_name)
                    .add("user_addr", user_addr)
                    .add("user_phone", user_phone)
                    .add("user_email", user_email)
                    .add("user_gender", user_gender)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_JOIN)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();

                Log.d(LOG_TAG, returnValue);

                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            if(paramValue.equals("1")){
                JoinActivity.joinSucceed = true;
            }
            else if(paramValue.equals("0")){
                JoinActivity.joinSucceed = false;
            }
        }
    }

    // 개인정보 수정 (modoo_user_modify_info)
    public static class modoo_user_modify_info extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String user_id = params[0];
            String user_name = params[1];
            String user_addr = params[2];
            String user_phone = params[3];
            String user_email = params[4];
            String user_gender = params[5];

            Log.d(LOG_TAG, user_id);

            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()
                    .add("user_id", user_id)
                    .add("user_name", user_name)
                    .add("user_addr", user_addr)
                    .add("user_phone", user_phone)
                    .add("user_email", user_email)
                    .add("user_gender", user_gender)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_USER_MODIFY_INFO)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();

                if(returnValue == "1")
                {
                    Log.d(LOG_TAG, "수정 완료");

                }
                else if(returnValue == "0")
                {
                    Log.d(LOG_TAG, "수정 실패");
                }


                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            Log.d(LOG_TAG, "returnValue = " + paramValue);
        }
    }

    // 내정보 보기 (modoo_user_modify_info)
    public static class modoo_user_modify_get extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String user_id = params[0];



            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()
                    .add("user_id", user_id)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_USER_MODIFY_GET)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();


                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            String[] rows = paramValue.split("$");
            MyInfoActivity.myInfo = rows[0];
        }
    }






    // 작가명 검색 (modoo_user_book_author)
    public static class modoo_user_book_author extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String book_author = params[0];

            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("book_author", book_author)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_USER_BOOK_AUTHOR)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();

                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            if(paramValue.equals("0")){
                return;
            }

            SearchActivity.searchedList = paramValue;

            /*
            String[] rows = paramValue.split("$");

            for(String row : rows){
                String[] col = row.split("#");
                String ret_book_name = col[0];
                String ret_book_author = col[1];
            }
            */

        }
    }



    // 책 이름 검색 (modoo_user_book_name)
    public static class modoo_user_book_name extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String book_name = params[0];

            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("book_name", book_name)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_USER_BOOK_NAME)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();

                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            if(paramValue.equals("0")){
                return;
            }

            SearchActivity.searchedList = paramValue;

           /* String[] rows = paramValue.split("$");

            for(String row : rows){
                String[] col = row.split("#");
                String ret_book_name = col[0];
                String ret_book_author = col[1];
            }*/

        }
    }


    // ISBN 검색 (modoo_user_book_isbn)
    public static class modoo_user_book_isbn extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String book_isbn = params[0];

            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("book_isbn", book_isbn)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_USER_BOOK_ISBN)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();

                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            if(paramValue.equals("0")){
                return;
            }

            SearchActivity.searchedList = paramValue;

            /*String[] rows = paramValue.split("$");

            for(String row : rows){
                String[] col = row.split("#");
                String ret_book_name = col[0];
                String ret_book_author = col[1];
            }*/

        }
    }


    // 자판기 주변 검색 (modoo_user_vm_around)
    public static class modoo_user_vm_around extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String myloc_lat = params[0];
            String myloc_lng = params[1];


            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("myloc_lat", myloc_lat)
                    .add("myloc_lng", myloc_lng)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_USER_VM_AROUND)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();

                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            String[] rows = paramValue.split("$");

            for(String row : rows){
                String[] col = row.split("#");
                String ret_vm_addr = col[0];
                String ret_vm_lat = col[1];
                String ret_vm_lng = col[2];
            }
        }
    }



    // 자판기 지역 검색 (modoo_user_vm_local)
    public static class modoo_user_vm_local extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String vm_addr = params[0];


            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("vm_addr", vm_addr)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_USER_VM_LOCAL)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();

                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            if(paramValue.equals("0")){
                return;
            }

            SearchActivity.searchedList = paramValue;

           /* String[] rows = paramValue.split("$");

            for(String row : rows){
                String[] col = row.split("#");
                String ret_book_name = col[0];
                String ret_book_author = col[1];
            }*/
        }
    }

    // 자판기 번호 검색 (modoo_user_vm_id)
    public static class modoo_user_vm_id extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String vm_id = params[0];


            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("vm_id", vm_id)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_USER_VM_ID)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();

                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            String[] rows = paramValue.split("$");

            for(String row : rows){
                String[] col = row.split("#");
                String ret_vm_addr = col[0];
                String ret_vm_lat = col[1];
                String ret_vm_lng = col[2];
            }
        }
    }


    // 사용자 대여 현황 조회 (modoo_user_state_borrow)
    public static class modoo_user_state_borrow extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String user_id = params[0];


            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("user_id", user_id)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_USER_STATE_BORROW)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();

                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            if(paramValue.equals("0")) {
                return;
            }

            MainActivity.borrowList = paramValue;


            String[] rows = paramValue.split("&");



                for (String row : rows) {
                    String[] col = row.split("#");
                    String ret_book_name = col[0];


                    MyInfoActivity.borrowCount++;
                }
            }
        }


    // 사용자 반납 현황 조회 (modoo_user_state_return)
    public static class modoo_user_state_return extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String user_id = params[0];


            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("user_id", user_id)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_USER_STATE_RETURN)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();

                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            if(paramValue.equals("0")) {
                return;
            }

            String[] rows = paramValue.split("$");

            if(rows.length > 0) {
            for(String row : rows) {
                String[] col = row.split("#");
                String ret_book_name = col[0];
                String ret_book_return_date = col[1];
                MyInfoActivity.returnCount++;
            }
            }
        }
    }



    //========================= 관리자 기능 =========================

    // 가입 승인 (modoo_admin_user_assign)
    public static class modoo_admin_user_assign extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String user_id = params[0];


            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("user_id", user_id)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_ADMIN_USER_ASSIGN)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();



                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            if(paramValue == "1"){
                Log.d(LOG_TAG, "가입 승인 처리 완료");
            }
            else if (paramValue == "0"){
                Log.d(LOG_TAG, "가입 승인 처리 실패");
            }

        }
    }



    // 회원 차단 (modoo_admin_user_refuse)
    public static class modoo_admin_user_refuse extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String user_id = params[0];


            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("user_id", user_id)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_ADMIN_USER_REFUSE)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();



                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            if(paramValue == "1"){
                Log.d(LOG_TAG, "가입 거절 처리 완료");
            }
            else if (paramValue == "0"){
                Log.d(LOG_TAG, "가입 거절 처리 실패");
            }

        }
    }



    // 반납 승인 (modoo_admin_return_confirm)
    public static class modoo_admin_return_confirm extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String user_id = params[0];
            String book_name = params[1];

            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("user_id", user_id)
                    .add("book_name", book_name)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_ADMIN_RETURN_CONFIRM)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();



                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            if(paramValue == "1"){
                Log.d(LOG_TAG, "반납 승인 완료");
            }
            else if (paramValue == "0"){
                Log.d(LOG_TAG, "반납 승인 거절");
            }
        }
    }



    // 자판기별 대출 현황 조회 (modoo_admin_state_borrow)
    public static class modoo_admin_state_borrow extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String vm_id = params[0];

            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("vm_id", vm_id)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_ADMIN_STATE_BORROW)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();



                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            String[] rows = paramValue.split("$");

            for(String row : rows){
                String[] col = row.split("#");
                String ret_vm_loc = col[0];
                String ret_vm_lat = col[1];
                String ret_vm_lng = col[2];
                String ret_book_name = col[3];
                String ret_book_borrow_date = col[4];
            }
        }
    }




    // 자판기별 반납 현황 조회 (modoo_admin_state_return)
    public static class modoo_admin_state_return extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String user_id = params[0];

            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("user_id", user_id)
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_ADMIN_STATE_RETURN)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();



                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            String[] rows = paramValue.split("$");

            for(String row : rows){
                String[] col = row.split("#");
                String ret_book_name = col[0];
                String ret_book_return_date = col[1];
            }
        }
    }



    // 월 별 사용자 도서 대출 통계 (modoo_admin_stat_month_borrow)
    public static class modoo_admin_stat_month_borrow extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_ADMIN_STAT_MONTH_BORROW)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();



                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            String[] rows = paramValue.split("$");

            for(String row : rows){
                String[] col = row.split("#");
                String ret_month = col[0];
                String ret_month_return_count = col[1];
            }
        }
    }

    // 월 별 사용자 반납 기능 통계 (modoo_admin_stat_month_return)
    public static class modoo_admin_stat_month_return extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_ADMIN_STAT_MONTH_RETURN)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();



                return returnValue;


            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            String[] rows = paramValue.split("$");

            for(String row : rows){
                String[] col = row.split("#");
                String ret_month = col[0];
                String ret_month_borrow_count = col[1];
            }
        }
    }

}
