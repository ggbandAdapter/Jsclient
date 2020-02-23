package cn.ggband.jsclient.client;

public interface Call<T> {
    void enqueue(Callback<T> callback);

}
