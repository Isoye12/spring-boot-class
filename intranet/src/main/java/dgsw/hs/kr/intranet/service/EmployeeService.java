package dgsw.hs.kr.intranet.service;

import dgsw.hs.kr.intranet.domain.Employee;
import dgsw.hs.kr.intranet.dto.JoinRequest;

public interface EmployeeService {
    public abstract Employee join (JoinRequest request);
    // public final static int age = 10;
}
