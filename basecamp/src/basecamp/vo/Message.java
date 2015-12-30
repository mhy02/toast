package basecamp.vo;
import java.util.Date;

public class Message {
	protected int no;
	protected String email;
	protected String content;
	protected Date createdDate;
	protected Date modifiedDate;
	
	public int getNo() {
		return no;
	}
	
	public Message setNo(int no) {
		this.no = no;
		return this;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public Message setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public String getContent() {
		return content;
	}
	
	public Message setContent(String content) {
		this.content = content;
		return this;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public Message setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public Date getModifieddDate() {
		return modifiedDate;
	}
	
	public Message setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}
}
