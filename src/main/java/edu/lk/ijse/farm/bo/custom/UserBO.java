package edu.lk.ijse.farm.bo.custom;

import edu.lk.ijse.farm.bo.SuperBO;
import edu.lk.ijse.farm.dto.UserDto;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDto userDto);
}
