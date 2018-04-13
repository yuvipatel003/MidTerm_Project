package yuvraj.midterm_project.ui.home.events;


import yuvraj.midterm_project.data.web.WebModel;

/**
 * Dispatched when a new web info will add or update
 */
public final class OnWebEvent {
	/**
	 * If null it will (treat) creating a brand new web info
	 */
	public final WebModel webModel;
	public final int webModelPosition;

	/**
	 * @param webModelPosition Position of the webinfo in the dataset. If a negative value will treat as new
	 */
	public OnWebEvent(WebModel webModel, int webModelPosition) {
		this.webModel = webModel;
		this.webModelPosition = webModelPosition;
	}

}
