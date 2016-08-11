package com.ink.trade.api.service;

import com.ink.trade.api.model.in.UnbindInput;
import com.ink.trade.api.model.out.UnbindOutput;

public interface IUnbindService {
  UnbindOutput unbind(UnbindInput unbindInput);
}
