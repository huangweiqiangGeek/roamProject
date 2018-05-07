package server;


public class CompLocationReportFileImpl implements CompLocationReport {

	@Override
	public void getAllUpdateItem(StringBuffer path) {
		// TODO Auto-generated method stub
		
	}

////	@Autowired(required = false)
////	DistService distService;
////	@Autowired(required = false)
////	BusVehiclesService busVehiclesService;
////	@Autowired(required = false)
////	CustomVehiclesService customVehiclesService;
////	@Autowired(required = false)
////	CustDistService custDistService;
////
////	FilenameFilter filter = new FilenameFilter() {
////
////		@Override
////		public boolean accept(File dir, String name) {
////			// TODO Auto-generated method stub
////			if (name.endsWith(".xml"))
////				return true;
////			return false;
////		}
////
////	};
////
////	public void getAllUpdateItem(StringBuffer path) {
////		File xmlFile = new File(path.toString());
////		if (!xmlFile.exists()) {
////			if (!xmlFile.mkdirs()) {
////				System.out.println("创建文件夹失败!");
////				return;
////			}
////		} else {
////			String[] filelist = xmlFile.list(filter);
////			for (String file : filelist) {
////				String className = MyXMLReader.getXMLRoot(new File(path
////						.toString() + file));
////				if (Constants.XML_CLASS_BUSVEHICLES.equals(className)) {
////					getAllDownLoadItemCar(path.toString(), file);
////				} else if (Constants.XML_CLASS_COPDISTITEM.equals(className)) {
////					getAllDownLoadItemDist(path.toString(), file);
////				}
////			}
////		}
////	}
////
////	private void getAllDownLoadItemCar(String path, String name) {
////		Map<String, Class> cm = new HashMap<String, Class>();
////		cm.put("BusVehicles", BusVehicles.class);
////		File readfile = new File(path + "\\" + name);
////		try {
////			BusVehicles car = (BusVehicles) XMLBeanUtils.xml2Bean(cm, readfile);
////			switch (car.getAppStatusId()) {
////			// 审批通过
////			case 23:
////				// 新增
////				if (car.getParentId() == null) {
////					CustomVehicles custCar = new CustomVehicles(car);
////					custCar.setCreate_time(DateUtil.getCurrentDate());
////					custCar.setStatus_id(custCar.getAppStatusId());
////					custCar.setPlat_status_id(16);
////					insertCustomCar(custCar);
////				} else {
////					CustomVehicles custCarOld = customVehiclesService
////							.queryCustomVehicleForId(car.getParentId());
////					// 变更
////					if (custCarOld.getStatus_id() == 24) {
////						CustomVehicles custCar = new CustomVehicles(car);
////						custCarOld.setStatus_id(27);
////						custCar.setCreate_time(DateUtil.getCurrentDate());
////						custCar.setStatus_id(custCar.getAppStatusId());
////						customVehiclesService.updateCustomVehicle(custCarOld);
////						insertCustomCar(custCar);
////						// 删除
////					} else if (custCarOld.getStatus_id() == 25) {
////						custCarOld.setStatus_id(58);
////						customVehiclesService.updateCustomVehicle(custCarOld);
//////						customVehiclesService.deleteCustomVehicles(custCarOld);
////					}
////				}
////
////				car = busVehiclesService.queryVehiclesByPlatNo(car).get(0);
////				busVehiclesService.deleteBusVehicles(car);
////				break;
////			// 审批拒绝
////			case 31:
////
////				BusVehicles oldCar = busVehiclesService.queryVehiclesByPlatNo(
////						car).get(0);
////				car.setId(oldCar.getId());
////				busVehiclesService.saveBusVehicles(car);
////				break;
////			}
////			readfile.delete();
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
////
////	private void insertCustomCar(CustomVehicles custCar) {
////
////		custCar.setId(null);
////		custCar.setPlat_status_id(16);// 自由车
////		// 申报人
////		custCar.setCreate_user_id(custCar.getUpdate_user_id());
////		custCar.setCreate_user_name(custCar.getUpdate_user_name());
////		custCar.setCreate_time(DateUtil.getCurrentDate());
////		custCar.setUpdate_time(null);
////		custCar.setUpdate_user_id(null);
////		custCar.setUpdate_user_name(null);
////
////		customVehiclesService.insertCustomVehicles(custCar);
////
////	}
////
////	private void insertCustomDist(CopDistItem item)
////			throws IllegalAccessException, InvocationTargetException {
////
////		CopDistHead head = item.getHead();
////		CustDistHead newHead = new CustDistHead();
////		BeanUtils.copyProperties(newHead, head);
////		newHead.setId(null);
////		// 申报人
////		newHead.setCreate_user_id(head.getUpdate_user_id());
////		newHead.setCreate_user_name(head.getUpdate_user_name());
////		newHead.setCreate_time(DateUtil.getCurrentDate());
////		newHead.setUpdate_time(null);
////		newHead.setUpdate_user_id(null);
////		newHead.setUpdate_user_name(null);
////		newHead.setStatusId(48);
////
////		custDistService.insertDistHead(newHead);
////		int distId = newHead.getId();
////		List<CopDistVehicles> vehiclesList = item.getVehiclesList();
////		for (CopDistVehicles car : vehiclesList) {
////			CustDistVehicles newCar = new CustDistVehicles();
////			BeanUtils.copyProperties(newCar, car);
////			newCar.setId(null);
////			newCar.setDistId(distId);
////			custDistService.saveDistVehicles(newCar);
////		}
////		List<CopDistBusinuss> businussList = item.getBusinussList();
////		for (CopDistBusinuss bus : businussList) {
////			CustDistBusinuss newBus = new CustDistBusinuss();
////			BeanUtils.copyProperties(newBus, bus);
////			newBus.setId(null);
////			newBus.setDistId(distId);
////			custDistService.saveDistBusinuss(newBus);
////		}
////		List<CopDistCargo> cargoList = item.getCargoList();
////		for (CopDistCargo cargo : cargoList) {
////			CustDistCargo newCargo = new CustDistCargo();
////			BeanUtils.copyProperties(newCargo, cargo);
////			newCargo.setId(null);
////			newCargo.setDistId(distId);
////			custDistService.saveDistCargo(newCargo);
////		}
////
////	}
////
////	private void getAllDownLoadItemDist(String path, String name) {
////		Map<String, Class> cm = new HashMap<String, Class>();
////		cm.put("CopDistItem", CopDistItem.class);
////		File readfile = new File(path + "\\" + name);
////		try {
////			CopDistItem item = (CopDistItem) XMLBeanUtils
////					.xml2Bean(cm, readfile);
////			CopDistHead head = item.getHead();
////			switch (head.getAppStatusId()) {
////			// 审批通过
////			case 48:
////				// 新增
////				if (head.getParentId() == null) {
////					insertCustomDist(item);
////				} else if(head.getParentId() == -1){
////					CustDistHead headOld = new CustDistHead();
////					headOld.setStatusId(53);
////					headOld.setUpdate_user_name("海关端");
////					headOld.setUpdate_time(head.getUpdate_time());
////					headOld.setDistNo(head.getDistNo());
////					custDistService.closeDistHeadAuto(headOld);
////				}else{
////
////					CustDistHead headOld = custDistService.selectDistHead(head
////							.getParentId());
////					// 变更
////					if (headOld.getStatusId() == 49) {
////						headOld.setStatusId(52);
////						headOld.setUpdate_user_id(head.getCreate_user_id());
////						headOld.setUpdate_user_name(head.getCreate_user_name());
////						headOld.setUpdate_time(new Date());
////						custDistService.updateDistHead(headOld);
////						insertCustomDist(item);
////					// 删除
////					} else if (headOld.getStatusId() == 50) {
////						headOld.setStatusId(59);
////						custDistService.updateDistHead(headOld);
//////						custDistService.deleteDistHead(headOld.getId());
////			        // 结案
////				    } else if (headOld.getStatusId() == 51) {
////						headOld.setStatusId(53);
////						headOld.setUpdate_user_id(head.getCreate_user_id());
////						headOld.setUpdate_user_name(head.getCreate_user_name());
////						headOld.setUpdate_time(new Date());
////						custDistService.updateDistHead(headOld);
////				    }
////				}
////
////				CopDistHead oldHead = this.distService
////						.queryDistHeadListByDistSeq(head.getDistSeq()).get(0);
////				distService.deleteDistHead(oldHead.getId());
////				break;
////			// 审批拒绝
////			case 31:
////
////				CopDistHead oldHead1 = this.distService
////						.queryDistHeadListByDistSeq(head.getDistSeq()).get(0);
////				head.setId(oldHead1.getId());
////				distService.saveDistHead(head);
////				break;
////			}
////
////			readfile.delete();
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	}
}