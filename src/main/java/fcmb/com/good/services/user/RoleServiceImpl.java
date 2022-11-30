package fcmb.com.good.services.user;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.userRequest.RoleRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.userResponse.RoleResponse;
import fcmb.com.good.model.entity.user.Role;
import fcmb.com.good.repo.user.RoleRepository;
import fcmb.com.good.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public ApiResponse<List<RoleResponse>> getListOfRoles(int page, int size) {
        List<Role> roleList = roleRepository.findAll(PageRequest.of(page,size)).toList();
        if(roleList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label,
                HttpStatus.OK.value(),
                Mapper.convertList(roleList, RoleResponse.class));
    }

    @Override
    public ApiResponse<RoleResponse> addRole(@RequestBody RoleRequest request) {
        Role role = Mapper.convertObject(request,Role.class);
        role=roleRepository.save(role);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(role, RoleResponse.class));
    }

    @Override
    public ApiResponse<RoleResponse> getRolesById(@RequestParam("id") UUID roleId) {
        Optional<Role> role = roleRepository.findByUuid(roleId);
        if(role.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        Role cm = role.get();
        return new ApiResponse<RoleResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(cm,RoleResponse.class));
    }

    private Role validateRole(UUID uuid){
        Optional<Role> role = roleRepository.findByUuid(uuid);
        if(role.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return role.get();
    }

    @Override
    public ApiResponse<RoleResponse> updateRole(UUID roleId, @RequestBody RoleRequest request) {
        Role role = validateRole(roleId);
        role.setDepartment(request.getDepartment());
        role = roleRepository.save(role);
        return new ApiResponse<RoleResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(role,RoleResponse.class));
    }

    @Override
    public ApiResponse<String> deleteRole(@RequestParam("id") UUID roleId) {
      Role role = validateRole(roleId);
      roleRepository.delete(role);
      return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");
    }




}
