import numpy as np, cv2, pyautogui, unique_vars

print(unique_vars.screensize)
print("Press escape key to close window")

while True: #Creates a screenshot to save as array lines 7-8, 9 BGR to RGB, 10-13 show images until escape key is pressed
    img = pyautogui.screenshot(region = unique_vars.desired_pixels)
    frame = np.array(img)
    frame = frame[...,::-1]
    cv2.imshow("screenshot", frame)
    instruct = cv2.imread('viewer_instructions.png', 1)
    cv2.imshow("instructions", instruct)
    if cv2.waitKey(unique_vars.time) & 255 == 27:
        break
cv2.destroyAllWindows() #removes windows when loop breaks, i.e. when esc pressed