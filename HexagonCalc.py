import numpy as np, cv2, pyautogui, unique_vars

print(unique_vars.screensize)
print("Press escape key to close window")

while True: #Creates repeated screenshots
    img = pyautogui.screenshot(region = unique_vars.desired_pixels)
    frame = np.array(img)
    end = frame.copy()
    frame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    edged = cv2.Canny(frame, 10, 300)
    '''cv2.imshow("name", edged)'''
<<<<<<< HEAD
=======
    '''line 7-9 create repeated/screenshots (a video, essentially) and output them as numpy arrays to'''
    '''convert to rgb in line 10'''
>>>>>>> c0f802d99d083470baa9d327550830dfce67b036
    kernel = np.ones((5,5))
    dil = cv2.dilate(edged, kernel, iterations=1)
    contours, hierarchy = cv2.findContours(dil, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_NONE)
    for each in contours:
        approx = cv2.approxPolyDP(each, cv2.arcLength(each, True), True)
        print(approx)
<<<<<<< HEAD
        if(len(approx) == 1):
            cv2.drawContours(end, contours, -1, (255, 0, 255), 8)
            area = cv2.contourArea(each)
            print(area)
=======
        if(len(approx) > 1):
            cv2.drawContours(end, contours, -1, (255, 0, 255), 8)
            area = cv2.contourArea(each)
            print(area)
    '''Above lines adjust image and if the contour is big enough, it'll show the image with the contours.'''
>>>>>>> c0f802d99d083470baa9d327550830dfce67b036
    cv2.imshow("name", end)
    if cv2.waitKey(unique_vars.time) & 255 == 27:
        break
cv2.destroyAllWindows() #removes windows when loop breaks, i.e. when esc pressed