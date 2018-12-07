package io.test.rail.api.client;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Created by alpa on 2018-12-06
 */
public class TestRailCallAdapter<T> extends CallAdapter.Factory {

    @Override
    public CallAdapter<T, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (returnType.getTypeName().startsWith(Call.class.getName())) {
            return null;
        }
        if (returnType.getTypeName().startsWith(retrofit2.Response.class.getName())) {
            return new ResponseCallAdapter(((ParameterizedType) returnType).getActualTypeArguments()[0]);
        }
        return new InstanceCallAdapter(returnType);
    }

    private static String getErrorMessage( retrofit2.Response<?> response) {
         ResponseBody errorBody = response.errorBody();
         String errorMessage;
        try {
            errorMessage = Objects.isNull(errorBody) ? response.message() : errorBody.string();
        } catch (IOException e) {
            throw new TestRailException("could not read error body", e);
        }
        return errorMessage;
    }

    private class ResponseCallAdapter implements CallAdapter<T, retrofit2.Response<T>> {

        private  Type returnType;

        ResponseCallAdapter( Type returnType) {
            this.returnType = returnType;
        }

        @Override
        public Type responseType() {
            return returnType;
        }

        @Override
        public retrofit2.Response<T> adapt(Call<T> call) {
             retrofit2.Response<T> response;
            try {
                response = call.execute();
            } catch (IOException e) {
                throw new TestRailException("Could not execute request", e);
            }

            if (!response.isSuccessful()) {
                throw new TestRailException(getErrorMessage(response));
            }
            return response;
        }
    }

    private class InstanceCallAdapter implements CallAdapter<T, Object> {
        
        private  Type returnType;

        InstanceCallAdapter(Type returnType) {
            this.returnType = returnType;
        }

        @Override
        public Type responseType() {
            return returnType;
        }

        @Override
        public Object adapt(Call<T> call) {
             retrofit2.Response<T> response;
            try {
                response = call.execute();
            } catch (IOException e) {
                throw new TestRailException("Could not get request body", e);
            }
            if (!response.isSuccessful()) {
                throw new TestRailException(getErrorMessage(response));
            }
            return response.body();
        }
    }

}
