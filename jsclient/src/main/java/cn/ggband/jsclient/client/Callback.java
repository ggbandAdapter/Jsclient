package cn.ggband.jsclient.client;

public interface Callback<T> {

    /**
     * Invoked for a received call js  response.
     *
     */
    void onResponse(T response);

    /**
     * Invoked when a call js exception  when an unexpected
     */
    void onFailure(Throwable t);
}
