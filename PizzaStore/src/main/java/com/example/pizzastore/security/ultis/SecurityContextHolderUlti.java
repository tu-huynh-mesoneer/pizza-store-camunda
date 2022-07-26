package com.example.pizzastore.security.ultis;

import com.example.pizzastore.entity.User;
import com.example.pizzastore.security.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * The Class SecurityContextHolderUlti.
 */
public class SecurityContextHolderUlti {

	/**
	 * Gets the current user.
	 *
	 * @return the current user
	 */
	public static User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserPrincipal) {
			return ((UserPrincipal) principal).getUser();
		} else {
			return null;
		}
	}

	/**
	 * Gets the current user name.
	 *
	 * @return the current user name
	 */
	public static String getCurrentUserName() {
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserPrincipal) {
				return ((UserPrincipal) principal).getUser().getName();
			} else {
				return SecurityContextHolder.getContext().getAuthentication().getName();
			}
		} catch (Exception e) {
			return "Unknow";
		}
	}
}
