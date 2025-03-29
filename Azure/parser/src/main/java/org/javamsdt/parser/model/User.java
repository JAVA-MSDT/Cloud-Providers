package org.javamsdt.parser.model;

import lombok.Data;

@Data
public class User {

    private String id;

    private String username;

    private String password;

    private String district;

    private boolean registered;

}
