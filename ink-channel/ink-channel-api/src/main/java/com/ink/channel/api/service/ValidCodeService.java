package com.ink.channel.api.service;

import com.ink.channel.api.model.in.ValidCodeInput;
import com.ink.channel.api.model.out.ValidCodeOutput;

public interface ValidCodeService {
    
    ValidCodeOutput getValidCode(ValidCodeInput validCodeInput);

}
