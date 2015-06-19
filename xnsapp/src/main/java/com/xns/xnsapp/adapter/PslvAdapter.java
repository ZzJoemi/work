package com.xns.xnsapp.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.xns.xnsapp.R;
import com.xns.xnsapp.beans.Arr_comment;
import com.xns.xnsapp.beans.Arr_tag;
import com.xns.xnsapp.beans.Lesson_list;
import com.xns.xnsapp.uis.PinnedSectionListView;



public class PslvAdapter extends BaseAdapter implements
		PinnedSectionListView.PinnedSectionListAdapter {
	private Context context;
	private List<Lesson_list> lesson_list;
	private BitmapUtils mBitmapUtils;
	private List<Item> items;

	public PslvAdapter(Context context, List<Lesson_list> lesson_list) {
		super();
		this.context = context;
		this.lesson_list = lesson_list;
//		BaseApp baseApp =(BaseApp)context.getApplicationContext();
//		this.mBitmapUtils=baseApp.mBitmapUtils;
		this.mBitmapUtils=new BitmapUtils(context);
		generateDataset();
	}

	private void generateDataset() {
		// TODO Auto-generated method stub
		// int sectionPosition = 0, listPosition = 0;
		items = new ArrayList<Item>();

		for (Lesson_list lesson : lesson_list) {
			Item itemHead = new Item();
			Item itemBody = new Item();
			itemHead.setAuthor_avatar(lesson.getAuthor_avatar());
			itemHead.setAuthor_nickname(lesson.getAuthor_nickname());
			itemHead.setAuthor_date(lesson.getAuthor_date());
			itemHead.setDate(lesson.getDate());
			itemHead.setContenttype(Item.SECTION);

			//////////////////////////////
			itemBody.setPoster(lesson.getPoster());
			itemBody.setArr_tag(lesson.getArr_tag());
			itemBody.setTitle(lesson.getTitle());
			itemBody.setSummary(lesson.getSummary());
			itemBody.setComment_count(lesson.getComment_count());
			itemBody.setArr_comment(lesson.getArr_comment());
			itemBody.setClicks(lesson.getClicks());
			itemBody.setContenttype(Item.ITEM);

			////////////////////////////

			items.add(itemHead);
			items.add(itemBody);

		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            Item item = items.get(position);
            if (item.contenttype == Item.SECTION) {
                    HeadViewHolder headHolder;
                    if (convertView != null && convertView instanceof RelativeLayout) {
                        headHolder = (HeadViewHolder) convertView.getTag();
                    } else {
                        convertView = LayoutInflater.from(context).inflate(
                                R.layout.item_list_main_header, null);
                        headHolder = new HeadViewHolder(convertView);
                        convertView.setTag(headHolder);
                    }
                //头部点击监听
                    headHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context,"点击了头部",Toast.LENGTH_SHORT).show();
                        }
                    });
                    headHolder.tvUserName.setText(item.getAuthor_nickname());
                    mBitmapUtils.display(headHolder.ivUserIcon,item.getAuthor_avatar());
                    headHolder.tvUserDate.setText(item.getAuthor_date());
                    headHolder.tvDate.setText(item.getDate());
                } else {
                    BodyViewHolder bodyHolder;
                    if (convertView != null && convertView instanceof LinearLayout) {
                        bodyHolder = (BodyViewHolder) convertView.getTag();
                    } else {
                        convertView = LayoutInflater.from(context).inflate(
                                R.layout.item_list_main_content, null);
                        bodyHolder = new BodyViewHolder(convertView);
                        convertView.setTag(bodyHolder);
                    }
                    String title = item.getTitle();
                    String img = item.getPoster();
                    String content = item.getSummary();
                    String commentcount = item.getComment_count();
                    String clicks = item.getClicks();
                    String likeCount = item.getThumbsup_count();
                    String shareCount = item.getShare_count();
                    // 评论内容
                    List<Arr_comment> arr_comments = item.getArr_comment();
                    //标签
                    List<Arr_tag> arr_tags = item.getArr_tag();
                    if (TextUtils.isEmpty(title)) {
                        bodyHolder.tvTitle.setVisibility(View.GONE);
                    } else {
                        bodyHolder.tvTitle.setText(title);
                    }

                    if (TextUtils.isEmpty(img)) {
                        bodyHolder.ivPoster.setVisibility(View.GONE);
                    } else {
                        mBitmapUtils.display(bodyHolder.ivPoster, img);
                    }

                    if (TextUtils.isEmpty(content)) {
                        bodyHolder.tvContent.setVisibility(View.GONE);
                    } else {
                        bodyHolder.tvContent.setText(content);
                    }
                    //评论条数
                    if (TextUtils.isEmpty(commentcount)) {
                        bodyHolder.linearAllComment.setVisibility(View.GONE);
                    } else {
                        bodyHolder.tvCommentCount.setText(commentcount);
                    }

                    if (arr_comments.size() == 0) {
                        bodyHolder.linearAllComment.setVisibility(View.GONE);
                    } else {
                        bodyHolder.linearAllComment.setVisibility(View.VISIBLE);
                        for (int i = 0; i < arr_comments.size(); i++) {
                            Arr_comment arr_comment = arr_comments.get(i);
                            TextView tvName = bodyHolder.tvCommentNames.get(i);
                            TextView tvBody = bodyHolder.tvCommentBodies.get(i);
                            tvName.setText(arr_comment.getNickname() + ": ");
                            tvBody.setText(arr_comment.getSummary());
                        }
                    }
                    if (TextUtils.isEmpty(clicks)) {
                        bodyHolder.tvClicks.setVisibility(View.GONE);
                    } else {
                        bodyHolder.tvClicks.setText(clicks);
                    }
                    //bodyHolder.gvTag.setVisibility(View.GONE);
    //			if(arr_tags.size()==0){
    //				bodyHolder.gvTag.setVisibility(View.GONE);
    //			}else{
    //				bodyHolder.gvTag.setAdapter(new TagAdapter(context,arr_tags));
    //			}
                    //底部部分
                    if (TextUtils.isEmpty(likeCount)) {
                        bodyHolder.tvBottomLikeCount.setText("0");
                    } else {
                        bodyHolder.tvBottomLikeCount.setText(likeCount);
                    }

                    if (TextUtils.isEmpty(commentcount)) {
                        bodyHolder.tvBottomCommentCount.setText("0");
                    } else {
                        bodyHolder.tvBottomCommentCount.setText(commentcount);
                    }

                    if (TextUtils.isEmpty(shareCount)) {
                        bodyHolder.tvBottomShareCount.setText("0");
                    } else {
                        bodyHolder.tvBottomShareCount.setText(shareCount);
                    }

                }
            return convertView;
        }

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		Item item = (Item) getItem(position);
		return item.contenttype;
	}

	@Override
	public boolean isItemViewTypePinned(int viewType) {
		return viewType == Item.SECTION;
	}



	class HeadViewHolder {

		ImageView ivUserIcon;
		//CircleImageView circleUserIcon;
		TextView tvUserName;
		TextView tvUserDate;
		TextView tvDate;
		RelativeLayout relativeLayout;
		public HeadViewHolder(View view) {
			relativeLayout=(RelativeLayout)view.findViewById(R.id.relative_header);
			ivUserIcon = (ImageView) view.findViewById(R.id.circle_usericon);
			//circleUserIcon=(CircleImageView)view.findViewById(R.id.circle_usericon);
			tvUserName = (TextView) view.findViewById(R.id.tv_username);
			tvUserDate=(TextView)view.findViewById(R.id.tv_marrytime);
			tvDate= (TextView) view.findViewById(R.id.tv_time);
		}
	}

	class BodyViewHolder {
		ImageView ivPoster;
		//GridView gvTag;
		TextView tvTitle;
		TextView tvContent;
		TextView tvCommentCount;
		TextView tvCommentName1;
		TextView tvCommentBody1;
		TextView tvCommentName2;
		TextView tvCommentBody2;
		TextView tvCommentName3;
		TextView tvCommentBody3;
		TextView tvBottomLikeCount;
		TextView tvBottomCommentCount;
		TextView tvBottomShareCount;
		TextView tvClicks;
		LinearLayout linearAllComment;
		List<TextView> tvCommentNames = new ArrayList<TextView>();
		List<TextView> tvCommentBodies=new ArrayList<TextView>();
		public BodyViewHolder(View view) {
			ivPoster = (ImageView) view.findViewById(R.id.iv_main_content);
			//gvTag=(GridView)view.findViewById(R.id.gv_tag);
			tvTitle = (TextView) view.findViewById(R.id.tv_main_title);
			tvContent=(TextView)view.findViewById(R.id.tv_main_content);
			tvCommentCount=(TextView)view.findViewById(R.id.tv_comment_count);
			tvCommentName1=(TextView)view.findViewById(R.id.item_user_commentname1);
			tvCommentBody1=(TextView)view.findViewById(R.id.item_user_commentbody1);
			tvCommentName2=(TextView)view.findViewById(R.id.item_user_commentname2);
			tvCommentBody2=(TextView)view.findViewById(R.id.item_user_commentbody2);
			tvCommentName3=(TextView)view.findViewById(R.id.item_user_commentname3);
			tvCommentBody3=(TextView)view.findViewById(R.id.item_user_commentbody3);
			tvBottomLikeCount=(TextView)view.findViewById(R.id.tv_comment_like);
			tvBottomCommentCount=(TextView)view.findViewById(R.id.tv_comment_pl);
			tvBottomShareCount=(TextView)view.findViewById(R.id.tv_comment_share);
			tvClicks=(TextView)view.findViewById(R.id.tv_comment_look);
			linearAllComment=(LinearLayout)view.findViewById(R.id.linear_all_comment);
			tvCommentNames.add(tvCommentName1);
			tvCommentNames.add(tvCommentName2);
			tvCommentNames.add(tvCommentName3);
			tvCommentBodies.add(tvCommentBody1);
			tvCommentBodies.add(tvCommentBody2);
			tvCommentBodies.add(tvCommentBody3);

		}
	}

		class Item {
		public static final int ITEM = 0;
		public static final int SECTION = 1;

		public int contenttype;
		String id;
		String type;
		String title;
		String poster;
		String summary;
		String clicks;
		String share_count;
		String date;
		List<Arr_tag> arr_tag;
		List<Arr_comment> arr_comment;
		String author_id;
		String author_nickname;
		String author_avatar;
		String author_date;
		String author_cert_type;
		String author_cert_desc;
		String is_collected;
		String is_thumbsup;
		String thumbsup_count;
		String comment_count;

		public void setType(String type) {
			this.type = type;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getPoster() {
			return poster;
		}

		public void setPoster(String poster) {
			this.poster = poster;
		}

		public String getSummary() {
			return summary;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

		public String getClicks() {
			return clicks;
		}

		public void setClicks(String clicks) {
			this.clicks = clicks;
		}

		public String getShare_count() {
			return share_count;
		}

		public void setShare_count(String share_count) {
			this.share_count = share_count;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public List<Arr_tag> getArr_tag() {
			return arr_tag;
		}

		public void setArr_tag(List<Arr_tag> arr_tag) {
			this.arr_tag = arr_tag;
		}

		public List<Arr_comment> getArr_comment() {
			return arr_comment;
		}

		public void setArr_comment(List<Arr_comment> arr_comment) {
			this.arr_comment = arr_comment;
		}

		public String getAuthor_id() {
			return author_id;
		}

		public void setAuthor_id(String author_id) {
			this.author_id = author_id;
		}

		public String getAuthor_nickname() {
			return author_nickname;
		}

		public void setAuthor_nickname(String author_nickname) {
			this.author_nickname = author_nickname;
		}

		public String getAuthor_avatar() {
			return author_avatar;
		}

		public void setAuthor_avatar(String author_avatar) {
			this.author_avatar = author_avatar;
		}

		public String getAuthor_date() {
			return author_date;
		}

		public void setAuthor_date(String author_date) {
			this.author_date = author_date;
		}

		public String getAuthor_cert_type() {
			return author_cert_type;
		}

		public void setAuthor_cert_type(String author_cert_type) {
			this.author_cert_type = author_cert_type;
		}

		public String getAuthor_cert_desc() {
			return author_cert_desc;
		}

		public void setAuthor_cert_desc(String author_cert_desc) {
			this.author_cert_desc = author_cert_desc;
		}

		public String getIs_collected() {
			return is_collected;
		}

		public void setIs_collected(String is_collected) {
			this.is_collected = is_collected;
		}

		public String getIs_thumbsup() {
			return is_thumbsup;
		}

		public void setIs_thumbsup(String is_thumbsup) {
			this.is_thumbsup = is_thumbsup;
		}

		public String getThumbsup_count() {
			return thumbsup_count;
		}

		public void setThumbsup_count(String thumbsup_count) {
			this.thumbsup_count = thumbsup_count;
		}

		public String getComment_count() {
			return comment_count;
		}

		public void setComment_count(String comment_count) {
			this.comment_count = comment_count;
		}

		public String getType() {
			return type;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getContenttype() {
			return contenttype;
		}

		public void setContenttype(int contenttype) {
			this.contenttype = contenttype;
		}
	}

}
