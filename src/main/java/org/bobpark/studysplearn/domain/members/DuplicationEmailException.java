package org.bobpark.studysplearn.domain.members;

public class DuplicationEmailException extends RuntimeException {

    public DuplicationEmailException() {
        super("duplicate email...");
    }
}
