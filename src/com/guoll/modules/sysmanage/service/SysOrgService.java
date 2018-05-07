package com.guoll.modules.sysmanage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guoll.modules.sysmanage.bean.SysOrg;
import com.guoll.modules.sysmanage.mapper.SysOrgMapper;

@Repository("sysOrgService")
public class SysOrgService {

	@Autowired
	private SysOrgMapper mapper;

	public List<SysOrg> queryBeanList(Integer parent_id) {

		List<SysOrg> returnList = new ArrayList<SysOrg>();

		queryByParentId(returnList, parent_id);

		return returnList;
	}

	public void queryByParentId(List<SysOrg> returnList, Integer parent_id) {

		List<SysOrg> list = mapper.queryByParentId(parent_id);

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				SysOrg org = list.get(i);
				returnList.add(org);
				queryByParentId(returnList, org.getId());
			}
		}

	}

	public void updateSysOrg(SysOrg sysOrg) {
		// TODO Auto-generated method stub
		mapper.updateSysOrg(sysOrg);
	}

	public void addSysOrg(SysOrg sysOrg) {
		// TODO Auto-generated method stub
		mapper.addSysOrg(sysOrg);
	}

	public void dragSysOrg(SysOrg sysOrg) {
		// TODO Auto-generated method stub
		mapper.dragSysOrg(sysOrg);
	}

	public void updateSysOrgName(SysOrg sysOrg) {
		// TODO Auto-generated method stub
		mapper.renameSysOrg(sysOrg);
	}

	public void deleteSysOrg(SysOrg sysOrg) {
		 //TODO Auto-generated method stub
		 List<SysOrg> sysOrgList = queryBeanList(sysOrg.getId());
		 for(SysOrg sysOrg_:sysOrgList){
		 mapper.deleteLinkSysOrgPostRole(sysOrg_);
		 mapper.deleteLinkSysOrgPostUser(sysOrg_);
		 mapper.deleteSysOrgPost(sysOrg_);
		 mapper.deleteSysOrgUser(sysOrg_);
		 mapper.deleteSysOrg(sysOrg_);
		 }
		 mapper.deleteLinkSysOrgPostRole(sysOrg);
		 mapper.deleteLinkSysOrgPostUser(sysOrg);
		 mapper.deleteSysOrgPost(sysOrg);
		 mapper.deleteSysOrgUser(sysOrg);
		 mapper.deleteSysOrg(sysOrg);
	}
}
