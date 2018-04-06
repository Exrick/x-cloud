package cn.exrick.xcloud.oauth.service;

import cn.exrick.xcloud.base.entity.Role;
import cn.exrick.xcloud.base.entity.User;
import cn.exrick.xcloud.common.constant.CommonConstant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Exrickx
 */
public class SecurityUserDetails extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    public SecurityUserDetails(User user) {

        if(user!=null) {
            this.setId(user.getId());
            this.setUsername(user.getUsername());
            this.setEmail(user.getEmail());
            this.setMobile(user.getMobile());
            this.setPassword(user.getCryptPassword());
            this.setSex(user.getSex());
            this.setAvatar(user.getAvatar());
            this.setStatus(user.getStatus());
            this.setCreateTime(user.getCreateTime());
            this.setRoles(user.getRoles());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<Role> roles = this.getRoles();
        if(roles!=null){
            for (Role role : roles) {
                authorityList.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return CommonConstant.STATUS_LOCK.equals(this.getStatus()) ? false : true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return CommonConstant.STATUS_ACTIVE.equals(this.getStatus()) ? true : false;
    }
}
