package cn.me.fdfs.service;

import cn.me.fdfs.vo.Line;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-8-30
 * Time: 上午11:39
 * To change this template use File | Settings | File Templates.
 */
public interface StructureService {

    List<Line> listStorageTopLine(String ip);

    List<Line> listStorageAboutFile(String ip);
}
