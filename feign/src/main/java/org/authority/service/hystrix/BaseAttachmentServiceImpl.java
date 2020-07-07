package org.authority.service.hystrix;

import java.util.List;

import org.authority.domain.Attachment;
import org.authority.service.IAttachmentService;
import org.authority.util.R;
import org.springframework.stereotype.Component;

@Component
public class BaseAttachmentServiceImpl implements IAttachmentService {

	@Override
	public R deleteByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R insert(Attachment record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R selectByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R updateByPrimaryKey(Attachment record) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R getAllRecordByPage(Attachment record, int currPage, int size) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R setDeleted(List<String> ids) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R batchSaveExcel(List<String[]> list) {
		return R.error(500, "服务器正忙");
	}

}
