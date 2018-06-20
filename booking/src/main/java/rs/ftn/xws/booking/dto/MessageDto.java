package rs.ftn.xws.booking.dto;

public class MessageDto {

	private String message;
	private boolean response;

	public MessageDto() {

	}

	public MessageDto(String message, boolean response) {
		this.message = message;
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isResponse() {
		return response;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}

}
