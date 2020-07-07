package org.authority.service.hystrix;

import java.util.List;

import org.authority.domain.Comment;
import org.authority.service.ICommentService;
import org.authority.util.R;
import org.springframework.stereotype.Component;

@Component
public class CommentServiceImpl implements ICommentService {

	@Override
	public R deleteByPrimaryKey(String id) {
		return R.error(500, "服务器正忙");
	}

	@Override
	public R insert(Comment record) {
		return R.error(500,"服务器正忙");
	}

	@Override
	public R selectByPrimaryKey(String id) {
		return R.error(500,"服务器正忙");
	}

	@Override
	public R updateByPrimaryKey(Comment record) {
		return R.error(500,"服务器正忙");
	}

	@Override
	public R getAllRecordByPage(Comment record, int currPage, int size) {
		return R.error(500,"服务器正忙");
	}

	@Override
	public R setDeleted(List<String> ids) {
		return R.error(500,"服务器正忙");
	}

	@Override
	public R batchSaveExcel(List<String[]> list) {
		return R.error(500,"服务器正忙");
	}

}
