
package jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jk.dao.ExportDao;
import jk.dao.PackingListDao;
import jk.domain.Export;
import jk.domain.PackingList;
import jk.pagination.Page;
import jk.service.PackingListService;
import jk.util.UtilFuns;

/**
 * @Description: TODO
 * @Author: tyler
 * @Company:
 * @CreateDate: 2017年2月22日
 */
@Service
public class PackingListServiceImpl implements PackingListService {

	@Resource
	PackingListDao packingListDao;
	@Resource
	ExportDao exportDao;

	@Override
	public List<PackingList> findPage(Page paramPage) {
		// TODO Auto-generated method stub
		return packingListDao.findPage(paramPage);
	}

	@Override
	public List<PackingList> find(Map paramMap) {
		// TODO Auto-generated method stub
		return packingListDao.find(paramMap);
	}

	@Override
	public PackingList get(Serializable paramSerializable) {
		// TODO Auto-generated method stub
		return packingListDao.get(paramSerializable);
	}

	@Override
	public void insert(PackingList paramPackingList) {
		// TODO Auto-generated method stub
		this.spellString(paramPackingList);
		paramPackingList.setId(UUID.randomUUID().toString());
		paramPackingList.setState(0); // 0草稿1已上报
		packingListDao.insert(paramPackingList);
	}

	@Override
	public void update(PackingList paramPackingList) {
		// TODO Auto-generated method stub
		this.spellString(paramPackingList);
		packingListDao.update(paramPackingList);
	}

	@Override
	public void deleteById(Serializable paramSerializable) {
		// TODO Auto-generated method stub
		Serializable[] ids = {paramSerializable};
		packingListDao.deleteById(paramSerializable);
	}

	@Override
	public void delete(Serializable[] paramArrayOfSerializable) {
		// TODO Auto-generated method stub
		packingListDao.delete(paramArrayOfSerializable);
	}

	// 拼接HTML片段
	public String getDivDataCreate(String[] exportIds) {
		StringBuffer sBuf = new StringBuffer();
		for (int i = 0; i < exportIds.length; i++) {
			Export export = exportDao.get(exportIds[i]);
			sBuf.append("<input type=\"checkbox\" name=\"exportIds\" checked value=\"").append(exportIds[i]).append("|")
					.append(export.getCustomerContract()).append("\" class=\"input\"/>");
			sBuf.append(export.getCustomerContract()).append("&nbsp;&nbsp;");
		}

		return sBuf.toString();
	}

	// 拼接HTML片段
	public String getDivDataUpdate(String[] exportIds, String[] exportNos) {
		StringBuffer sBuf = new StringBuffer();
		for (int i = 0; i < exportIds.length; i++) {
			sBuf.append("<input type=\"checkbox\" name=\"exportIds\" checked value=\"").append(exportIds[i]).append("|")
					.append(exportNos[i]).append("\" class=\"input\"/>");
			sBuf.append(exportNos[i]).append("&nbsp;&nbsp;");
		}

		return sBuf.toString();
	}

	// 拼接HMTL片段
	public String getDivDataView(String[] exportNos) {
		StringBuffer sBuf = new StringBuffer();
		for (int i = 0; i < exportNos.length; i++) {
			sBuf.append(exportNos[i]).append("&nbsp;&nbsp;");
		}

		return sBuf.toString();
	}

	// 拆串，拼串
	private PackingList spellString(PackingList packingList) {
		String _exportIds = "";
		String _exportNos = "";

		String[] _s = packingList.getExportIds().split(","); // id|no
		for (int i = 0; i < _s.length; i++) {
			String[] _tmp = _s[i].split("\\|"); // 正则表达式，转义
			_exportIds += _tmp[0] + "|";
			_exportNos += _tmp[1] + "|";
		}
		_exportIds = UtilFuns.delLastChar(_exportIds);
		_exportNos = UtilFuns.delLastChar(_exportNos);

		packingList.setExportIds(_exportIds);
		packingList.setExportNos(_exportNos);

		return packingList;
	}

}
