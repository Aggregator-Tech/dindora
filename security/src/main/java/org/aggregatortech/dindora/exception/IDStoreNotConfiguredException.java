package org.aggregatortech.dindora.exception;

public class IDStoreNotConfiguredException extends BaseException {
    public IDStoreNotConfiguredException(String errCode) {
        super(errCode);
    }

    public IDStoreNotConfiguredException(String errCode, Exception ex) {
        super(errCode,ex);

    }
}
