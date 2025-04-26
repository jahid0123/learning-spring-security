package com.jmjbrothers.learn_spring_security.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {

	private String username;
	private String password;
}
