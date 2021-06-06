package com.uoc.clockin.app.oauth.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.uoc.clockin.app.commons.models.entity.Company;
import com.uoc.clockin.app.commons.models.entity.Role;
import com.uoc.clockin.app.commons.models.entity.TypeRole;
import com.uoc.clockin.app.commons.models.entity.User;
import com.uoc.clockin.app.oauth.services.ICompanyService;
import com.uoc.clockin.app.oauth.services.IUserService;

@Component
public class AdditionalInfoToken implements TokenEnhancer{

	@Autowired
	private IUserService userService;
	@Autowired
	private ICompanyService companyService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		User user = userService.findByUsername(authentication.getName());
		Company company = companyService.findById((long)user.getIdCompany());
		info.put("name", user.getName());
		info.put("email", user.getEmail());
		info.put("userId", user.getId());
		info.put("companyId", user.getIdCompany());
		info.put("isAdmin", isAdmin(user.getRoles()));
		info.put("companyName", company.getName());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}
	
	private boolean isAdmin(List<Role> roles) {
		for(Role role : roles) {
			if(role.getType() == TypeRole.ROLE_ADMIN) {
				return true;
			}
		}
		return false;
	}

}
