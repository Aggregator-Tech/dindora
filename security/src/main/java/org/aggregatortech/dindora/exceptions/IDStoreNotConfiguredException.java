package org.aggregatortech.dindora.exceptions;

public class IDStoreNotConfiguredException extends BaseException {
    public IDStoreNotConfiguredException(String errCode) {
        super(errCode);
    }

    public IDStoreNotConfiguredException(String errCode, Exception ex) {
        super(errCode,ex);

    }
}
