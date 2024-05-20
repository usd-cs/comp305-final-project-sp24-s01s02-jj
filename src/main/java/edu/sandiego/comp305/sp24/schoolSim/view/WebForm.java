package edu.sandiego.comp305.sp24.schoolSim.view;

public interface WebForm {
    /**
     * Add the entity this form represents into the database.
     */
    void build();

    /**
     * Get the path to this form.
     *
     * @return The absolute path to this form.
     */
    String getFormPath();

    /**
     * Get the redirect string for a successful form submission.
     *
     * @return Where to redirect to upon a form submission.
     */
    String getSuccessRedirect();

    String getUnsuccessfulRedirect();

}
