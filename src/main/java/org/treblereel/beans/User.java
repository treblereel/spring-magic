package org.treblereel.beans;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

//@Component("userBean")  <-- because of xml config
@Transactional
public class User implements UserInterface{
	private String username;

	@InjectRundomInt(min = 4, max = 7)
	private int repeat = 0;

	public User() {
		System.out.println("Constractor");
	}
	
	/* We must turn on in applicationContext, but i is not transactional, because
	 * postProcessAfterInitialization didn't make proxy
	 */
	@PostConstruct
	private void postConstructor(){
		System.out.println("postConstructor");
	}

	@Override
	public void sayHello() {
		for (int i = 0; i < repeat; i++) {
			System.out.println("Hello " + username);
		}
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public int getRepeat() {
		return repeat;
	}

	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}

}
