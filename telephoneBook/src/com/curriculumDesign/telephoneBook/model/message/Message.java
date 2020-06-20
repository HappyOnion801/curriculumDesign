package com.curriculumDesign.telephoneBook.model.message;

import com.curriculumDesign.telephoneBook.model.Header;

import java.io.Serializable;

/**
 * @ Author: MaCode
 * @ Date: 2020-06-18
 * @ Github: HappyOnion801
 */
public interface Message extends Serializable {
    Header getHeader();
    Object getBody();
}
