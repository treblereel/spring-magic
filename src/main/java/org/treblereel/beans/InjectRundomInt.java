package org.treblereel.beans;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // Make this annotation accessible at runtime via reflection. import added handly
//@Target({ElementType.METHOD})       // This annotation can only be applied to class methods.
public @interface InjectRundomInt {

	int min();  // min = 4 at User
	int max();  // max = 7 at User

}
