package com.example.traveler.controller;

import com.example.traveler.config.BaseException;
import com.example.traveler.config.BaseResponse;
import com.example.traveler.model.dto.*;
import com.example.traveler.service.DayCourseService;
import com.example.traveler.service.SpotService;
import com.example.traveler.service.TravelService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.traveler.config.BaseResponseStatus.DELETE_TRAVEL_FAIL;
import static com.example.traveler.config.BaseResponseStatus.SAVE_TRAVEL_FAIL;

@RestController
@RequestMapping("/travel")
public class TravelController {
    @Autowired
    private TravelService travelService;
    @Autowired
    private DayCourseService dayCourseService;
    @Autowired
    private SpotService spotService;
    @PostMapping("")
    public BaseResponse<TravelResponse> saveTravel(@RequestBody TravelRequest travelRequest) {
        try {
            TravelResponse travelResponse = travelService.saveTravel(travelRequest);
            return new BaseResponse<>(travelResponse);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("")
    public BaseResponse<List<TravelResponse>> getAllTravel() {
        try {
            List<TravelResponse> travelResponses = travelService.getAllTravel();
            return new BaseResponse<>(travelResponses);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

//    @GetMapping("/my")
//    public List<TravelResponse> getAllMyTravel(User user) {
//        return travelService.getAllMyTravel(user);
//    }

    @GetMapping("/{tId}")
    public BaseResponse<TravelResponse> getTravel(@PathVariable("tId") int tId) {
        try {
            TravelResponse travelResponse = travelService.getTravel(tId);
            return new BaseResponse<>(travelResponse);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    //test 안함
    @PatchMapping("/{tId}")
    public BaseResponse<TravelResponse> patchTravel(@PathVariable("tId") int tId, @RequestBody TravelRequest travelRequest) {
        try {
            TravelResponse travelResponse = travelService.patchTravel(tId, travelRequest);
            return new BaseResponse<>(travelResponse);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @DeleteMapping("/{tId}")
    public BaseResponse<String> deleteTravel(@PathVariable("tId") int tId){
        try {
            int result = travelService.deleteTravel(tId);
            if (result == 0) {
                throw new BaseException(DELETE_TRAVEL_FAIL);
            } else {
                return new BaseResponse<>("여행 삭제에 성공했습니다.");
            }
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @PostMapping("/{tId}/course")
    public BaseResponse<DayCourseResponse> saveCourse(@PathVariable("tId") int tId, @RequestBody DayCourseRequest dayCourseRequest) {
        try {
            DayCourseResponse dayCourseResponse = dayCourseService.saveCourse(dayCourseRequest, tId);
            return new BaseResponse<>(dayCourseResponse);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


    @GetMapping("/course/{dcId}")
    public BaseResponse<DayCourseResponse> getCourse(@PathVariable("dcId") int dcId) {
        try {
            DayCourseResponse dayCourseResponse = dayCourseService.getCourse(dcId);
            return new BaseResponse<>(dayCourseResponse);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/{tId}/course")
    public BaseResponse<List<DayCourseResponse>> getAllDayCourseByTravel(@PathVariable("tId") int tId) {
        try {
            List<DayCourseResponse> dayCourseResponses =  dayCourseService.getAllDayCourseByTravel(tId);
            return new BaseResponse<>(dayCourseResponses);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @PostMapping("/course/{dcId}/spot")
    public BaseResponse<DayCourseResponse> saveSpot(@PathVariable("dcId") int dcId, @RequestBody SpotRequest spotRequest) {
        try {
            DayCourseResponse dayCourseResponse =  spotService.saveSpot(spotRequest, dcId);
            return new BaseResponse<>(dayCourseResponse);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @DeleteMapping("/course/{dcId}/spot/{num}")
    public BaseResponse<DayCourseResponse> deleteSpot(@PathVariable("dcId") int dcId, @PathVariable("num") int num) {
        try {
            DayCourseResponse dayCourseResponse =  spotService.deleteSpot(dcId, num);
            return new BaseResponse<>(dayCourseResponse);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}