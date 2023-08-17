/**형식 체크 하는 validator
 * 다양한 타입의 객체에 대한 유효성 검사 수행하기 위한 check 메서드
 * 이런것들은 일단 만들어 두고 돌려 쓰는 용도라고 이해하면 편할 듯*/
package com.test.validators;

public interface Validator<T> {
    void check(T t);
}
