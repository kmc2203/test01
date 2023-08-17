/**들어온 정보가 공백이거나 null 인지 체크하는 인터페이스
 * 이런 validator interface 들은 돌려 쓰는 용도라고 이해하면 편할 듯*/
package com.test.validators;

public interface RequiredValidator {
    default void checkRequired(String str, RuntimeException e){
        if(str == null || str.isBlank()){ // str 이 null 이거나 공백이면
            throw e;
        }
    }
}
