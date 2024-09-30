package modelLibraries;

/**
 * 
 * @author fgutierrezc@unah.hn
 * @version 0.1.0
 * @date 02/05/2023
 * @description Esta clase contiene el modelo del DTO que se guardará en la sesión
 *
 */

public class DTORegistro {
	
	private int totalTwitts;
	private int likes;
	private int shares;
	private int comments;
	private int visual;

	public DTORegistro() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the totalTwitts
	 */
	public int getTotalTwitts() {
		return totalTwitts;
	}

	/**
	 * @param totalTwitts the totalTwitts to set
	 */
	public void setTotalTwitts(int totalTwitts) {
		this.totalTwitts = totalTwitts;
	}

	/**
	 * @return the likes
	 */
	public int getLikes() {
		return likes;
	}

	/**
	 * @param likes the likes to set
	 */
	public void setLikes(int likes) {
		this.likes = likes;
	}

	/**
	 * @return the shares
	 */
	public int getShares() {
		return shares;
	}

	/**
	 * @param shares the shares to set
	 */
	public void setShares(int shares) {
		this.shares = shares;
	}

	/**
	 * @return the comments
	 */
	public int getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(int comments) {
		this.comments = comments;
	}

	/**
	 * @return the visual
	 */
	public int getVisual() {
		return visual;
	}

	/**
	 * @param visual the visual to set
	 */
	public void setVisual(int visual) {
		this.visual = visual;
	}

}
