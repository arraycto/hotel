package com.yangkai.hotel.main.service.impl;

import com.github.pagehelper.PageHelper;
import com.yangkai.hotel.main.dao.RmsRoomDao;
import com.yangkai.hotel.main.service.RmsRoomService;
import com.yangkai.hotel.mbg.mapper.RmsRoomMapper;
import com.yangkai.hotel.mbg.model.RmsRoom;
import com.yangkai.hotel.mbg.model.RmsRoomExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (RmsRoom)表服务实现类
 *
 * @author makejava
 * @since 2020-10-12 09:45:54
 */
@Service("rmsRoomService")
public class RmsRoomServiceImpl implements RmsRoomService {
    @Autowired
    private RmsRoomDao rmsRoomDao;
    @Autowired
    RmsRoomMapper rmsRoomMapper;


    @Override
    public List<RmsRoom> list(RmsRoom room, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        RmsRoomExample example = new RmsRoomExample();
        RmsRoomExample.Criteria criteria = example.createCriteria();
        if (room.getDescription() != null) {
            criteria.andNameLike("%" + room.getName() + "%");
            criteria.andDescriptionLike("%" + room.getDescription() + "%");
        }
        if (room.getFloor() != null) {
            criteria.andFloorEqualTo( room.getFloor());
        }
        if (room.getSerial() != null) {
            criteria.andSerialEqualTo(room.getSerial() );
        }
        if (room.getClean() != null) {
            criteria.andCleanEqualTo(room.getClean() );
        }
        if (room.getStatus() != null) {
            criteria.andStatusEqualTo( room.getStatus() );
        }
        if (room.getMaintenance() != null) {
            criteria.andMaintenanceEqualTo(room.getMaintenance() );
        }
        example.setOrderByClause("sort");
        return rmsRoomMapper.selectByExample(example);
    }

    @Override
    public RmsRoom getRoomById(Long id) {
        return rmsRoomDao.queryById(id);
    }

    @Override
    public int updateRoom(RmsRoom room) {
        return rmsRoomDao.update(room);
    }


    @Override
    public int addRoom(RmsRoom room) {
        return rmsRoomDao.insert(room);
    }
    @Override
    public int deleteById(Long id) {
        return rmsRoomDao.deleteById(id);
    }

}