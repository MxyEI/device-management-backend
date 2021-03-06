package com.hik.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

import com.hik.dao.UserWithDeviceAstMapper;
import com.hik.dao.DeviceWithUserMapper;
import com.hik.entity.DeviceWithUser;
import com.hik.entity.vo.UserDeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:用户设备ServiceImpl
 * @author: mxy
 *
 */
@Service
public class UserDeviceServiceImpl implements IUserDeviceService {
	static DeviceWithUser dw = null;
	int temp = 0;
	@Autowired
	DeviceWithUserMapper userDeviceMapper;

	@Autowired
	UserWithDeviceAstMapper userDeviceAstMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
//		Future<Integer> future = null;
//		ExecutorService pool = Executors.newSingleThreadExecutor();
//		try{
//			future = pool.submit(new Callable<Integer>() {
//									 @Override
//									 public Integer call() throws Exception {
//										 temp = userDeviceMapper.deleteByPrimaryKey(id);
//										 return temp;
//									 }
//								 }
//			);
//
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		while(true){
//			try {
//				return future.get();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				e.printStackTrace();
//			}
//		}
		return userDeviceMapper.deleteByPrimaryKey(id);
	}


	@Override
	public int insertSelective(DeviceWithUser record) {
		return userDeviceMapper.insertSelective(record);
	}

	@Override
	public DeviceWithUser selectByPrimaryKey(Integer id) {
		return userDeviceMapper.selectByPrimaryKey(id);
	}

	@Override
	public DeviceWithUser selectByuidjid(Map map) {
		return userDeviceMapper.selectByuidjid(map);
	}

	@Override
	public List<DeviceWithUser> selectByuid(Map map) {
		return userDeviceMapper.selectByuid(map);
	}

	@Override
	public Long selectByuidCounts(Map map) {
		return userDeviceMapper.selectByuidCounts(map);
	}

	@Override
	public int updateByPrimaryKeySelective(DeviceWithUser record) {
		return userDeviceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DeviceWithUser record) {
		return userDeviceMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UserDeviceVO> findAscUserJobs(Map<String, Object> map) {
		return userDeviceAstMapper.findAscUserJobs(map);
	}

	@Override
	public List<UserDeviceVO> findAscUserJobsWithSuccess(Map<String, Object> map) {
		return userDeviceAstMapper.findAscUserJobsWithSuccess(map);
	}

	@Override
	public Long getTotlaAscUserJobs(Map<String, Object> map) {
		return userDeviceAstMapper.getTotlaAscUserJobs(map);
	}

	@Override
	public Long getTotlaAscUserJobsWithSuccess(Map<String, Object> map) {
		return userDeviceAstMapper.getTotlaAscUserJobsWithSuccess(map);
	}

    @Override
    public List<UserDeviceVO> findAscUserJobsByUserid(Map<String, Object> map) {
        return userDeviceAstMapper.findAscUserJobsByUserid(map);
    }

	@Override
	public List<UserDeviceVO> findAscUserJobsWithUserid(Map<String,Object> map) {
		return userDeviceAstMapper.findAscUserJobsWithUserid(map);
	}

	@Override
	public Long getTotlaAscUserJobsWithuser(Map<String,Object> map) {
		return userDeviceAstMapper.getTotlaAscUserJobsWithuser(map);
	}



}
