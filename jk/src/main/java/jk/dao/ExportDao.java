package jk.dao;

import java.io.Serializable;
import java.util.Map;

import jk.domain.Export;



/**
 * @author zhangtai
 *
 */
public interface ExportDao extends BaseDao<Export> {

	public abstract void updateState(Map paramMap);
}
