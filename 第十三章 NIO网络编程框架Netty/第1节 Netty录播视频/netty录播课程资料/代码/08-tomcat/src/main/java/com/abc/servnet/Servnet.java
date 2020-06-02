package com.abc.servnet;

/**
 * servnet规范
 */
public abstract class Servnet {
    protected abstract void doGet(NettyRequest request, NettyReponse response) throws Exception;
    protected abstract void doPost(NettyRequest request, NettyReponse response)throws Exception;

}
