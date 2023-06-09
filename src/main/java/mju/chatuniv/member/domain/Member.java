package mju.chatuniv.member.domain;

import mju.chatuniv.member.exception.MemberEmailFormatInvalidException;
import mju.chatuniv.member.exception.MemberPasswordBlankException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.regex.Pattern;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    protected Member() {
    }

    private Member(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    public static Member from(final String email, final String password) {
        if (!isEmailFormat(email)) {
            throw new MemberEmailFormatInvalidException(email);
        }

        if (isEmpty(password)) {
            throw new MemberPasswordBlankException();
        }

        return new Member(email, password);
    }

    private static boolean isEmailFormat(final String email) {
        return Pattern.matches("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", email);
    }

    private static boolean isEmpty(final String password) {
        return password == null || password.isBlank();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
