#version 430

layout (location = 0) in vect2 position0;

uniform mat4 localMatrix;
uniform mat4 worldMatrix;

void main(){

	vec2 localPosition = (localMatrix * vect4(position0.x,0,position0.y,1)).xz;

	gl_Position = worldMatrix * vec4(localPosition.x,0,localPosition.y,1);
}
