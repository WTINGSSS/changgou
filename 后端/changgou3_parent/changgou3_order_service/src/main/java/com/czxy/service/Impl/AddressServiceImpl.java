package com.czxy.service.Impl;

import com.czxy.mapper.AddressMapper;
import com.czxy.pojo.Address;
import com.czxy.pojo.User;
import com.czxy.service.AddressService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 庭前云落
 * @Date 2020/5/3 19:32
 * @description
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    /**
     * 查询指定用户所有地址
     */
    public List<Address> findAlistByUserId(Integer userId) {
        Example example = new Example(Address.class);
        Example.Criteria c = example.createCriteria();
        c.andEqualTo("userId", userId);
        return addressMapper.selectByExample(example);
    }

    /**
     * 添加地址
     */
    public void addAddress(User user, Address address) {
        addressMapper.updateDefault(user.getId(),0);
        address.setIsdefault(1);
        this.addressMapper.insert(address);
    }
}
