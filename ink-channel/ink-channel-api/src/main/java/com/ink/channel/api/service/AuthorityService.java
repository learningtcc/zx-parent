package com.ink.channel.api.service;

import com.ink.channel.api.model.in.AuthorityInput;
import com.ink.channel.api.model.out.AuthorityOutput;

public interface AuthorityService {
    public AuthorityOutput authorize(AuthorityInput authority);
}
