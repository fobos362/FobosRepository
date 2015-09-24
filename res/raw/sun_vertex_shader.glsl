uniform mat4 ModelViewProjectionMatrix;
attribute vec4 Vertex;
attribute vec2 MultiTexCoord0;

varying vec2 rm_Texcoord;

void main( void )
{
    gl_Position = ModelViewProjectionMatrix * Vertex;
    rm_Texcoord    = MultiTexCoord0.xy;
}